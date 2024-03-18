package com.olim.authservice.service;


import com.olim.authservice.dto.response.enduser.JwtDto;
import com.olim.authservice.enumeration.OAuthServerType;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.transaction.annotation.Transactional;

public interface Oauth2Service {

    @Transactional
    String getAuthCodeRequestUrl(OAuthServerType oauthServerType);
    @Transactional
    JwtDto login(OAuthServerType oauthServerType, String authCode, HttpServletResponse response, String ip);
}
