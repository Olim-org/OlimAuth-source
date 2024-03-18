package com.olim.authservice.service.impl;

import com.olim.authservice.config.JwtTokenProvider;
import com.olim.authservice.dto.response.enduser.JwtDto;
import com.olim.authservice.entity.EndUser;
import com.olim.authservice.entity.OauthMember;
import com.olim.authservice.enumeration.OAuthServerType;
import com.olim.authservice.oauth2.AuthCodeRequestUrlProviderComposite;
import com.olim.authservice.oauth2.OauthMemberClientComposite;
import com.olim.authservice.repository.EndUserRepository;
import com.olim.authservice.repository.OauthMemberRepository;
import com.olim.authservice.service.Oauth2Service;
import com.olim.authservice.service.RedisUtilService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class Oauth2ServiceImpl implements Oauth2Service {



    @Value("${AWS.Domain}")
    private String AwsDomain;

    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate<String, Object> redisTemplate;
    private final AuthCodeRequestUrlProviderComposite authCodeRequestUrlProviderComposite;
    private final OauthMemberClientComposite oauthMemberClientComposite;
    private final OauthMemberRepository oauthMemberRepository;
    private final EndUserRepository endUserRepository;
    private final RedisUtilService redisUtilService;
    private long refreshTime = 14 * 24 * 60 * 60L;

    @Transactional
    @Override
    public String getAuthCodeRequestUrl(OAuthServerType oauthServerType) {
        return this.authCodeRequestUrlProviderComposite.provide(oauthServerType);
    }

    @Transactional
    @Override
    public JwtDto login(OAuthServerType oauthServerType, String authCode, HttpServletResponse response, String ip) {

         OauthMember oauthMember = this.oauthMemberClientComposite.fetch(oauthServerType, authCode);

        EndUser endUser = this.oauthMemberClientComposite.fetchEnduser(oauthServerType, oauthMember);

        OauthMember saved = this.oauthMemberRepository.findByOauthId(oauthMember.oauthId())
                .orElseGet(() -> this.oauthMemberRepository.save(oauthMember));
        EndUser savedEndUser = this.endUserRepository.findByEmail(endUser.getEmail()).orElseGet(
                () -> this.endUserRepository.save(endUser)
        );
        String email = endUser.getEmail();
        String aToken = jwtTokenProvider.createToken(email, savedEndUser.getRole(), savedEndUser.getId());

        String refreshToken = jwtTokenProvider.CreateRefreshToken(email);
//        redisTemplate.opsForValue().set(savedEndUser.getEmail(), refreshToken, refreshTime);
        this.redisUtilService.setDataExpire(savedEndUser.getEmail(), refreshToken, refreshTime);
        addTokenAndCookieToResponse(response, refreshToken, AwsDomain);

        JwtDto jwtDto = new JwtDto(aToken);

        log.info("Oauth 로그인 완료 email: {}, datetime: {}, ip address: {}", email, LocalDateTime.now(), ip);
        return jwtDto;

    }

    private void addTokenAndCookieToResponse(HttpServletResponse response, String refreshToken, String awsDomain) {
        // Add access token to the response header

        // Create and configure a cookie for the refresh token
        Cookie cookie = new Cookie("refresh_token", refreshToken);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24); // 1 day
        cookie.setSecure(true);  // 추후 https 구현시 true로
        cookie.setAttribute("SameSite", "None"); // 추후 같은 사이트에서만 실행할 수 있게 변경
        cookie.setHttpOnly(true);
        cookie.setDomain(awsDomain);

        // Add the cookie to the response
        response.addCookie(cookie);
    }



}
