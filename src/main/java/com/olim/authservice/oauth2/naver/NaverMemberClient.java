package com.olim.authservice.oauth2.naver;


import com.olim.authservice.entity.EndUser;
import com.olim.authservice.entity.OauthMember;
import com.olim.authservice.enumeration.OAuthServerType;
import com.olim.authservice.enumeration.SocialType;
import com.olim.authservice.enumeration.UserRoleEnum;
import com.olim.authservice.oauth2.OauthMemberClient;
import com.olim.authservice.oauth2.config.NaverOauthConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class NaverMemberClient implements OauthMemberClient {

    private final NaverApiClient naverApiClient;
    private final NaverOauthConfig naverOauthConfig;

    @Override
    public OAuthServerType supportServer() {
        return OAuthServerType.NAVER;
    }

    @Override
    public OauthMember fetch(String authCode) {
        NaverToken tokenInfo = naverApiClient.fetchToken(tokenRequestParams(authCode));
        NaverMemberResponse naverMemberResponse = naverApiClient.fetchMember("Bearer " + tokenInfo.accessToken());
        return naverMemberResponse.toDomain();
    }

    @Override
    public EndUser fetchEnduser(OAuthServerType oAuthServerType, OauthMember oauthMember) {

        return EndUser.createUser(
                null,
                null,
                null,
                null,
                null,
                null,
                oauthMember.email(),
                null,
                oauthMember.profileImageUrl(),
                null,
                null,
                null,
                SocialType.NAVER,
                String.valueOf(oauthMember.id()),
                UserRoleEnum.ROLE_GUEST
        );
    }

    private MultiValueMap<String, String> tokenRequestParams(String authCode) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", naverOauthConfig.clientId());
        params.add("client_secret", naverOauthConfig.clientSecret());
        params.add("code", authCode);
        params.add("state", naverOauthConfig.state());
        return params;
    }

}

