package com.olim.authservice.oauth2.google;


import com.olim.authservice.entity.EndUser;
import com.olim.authservice.entity.OauthMember;
import com.olim.authservice.enumeration.OAuthServerType;
import com.olim.authservice.enumeration.SocialType;
import com.olim.authservice.enumeration.UserRoleEnum;
import com.olim.authservice.oauth2.OauthMemberClient;
import com.olim.authservice.oauth2.config.GoogleOauthConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class GoogleMemberClient implements OauthMemberClient {


    private final GoogleApiClient googleApiClient;
    private final GoogleOauthConfig googleOauthConfig;

    @Override
    public OAuthServerType supportServer() {
        return OAuthServerType.GOOGLE;

    }

    @Override
    public OauthMember fetch(String code) {
        GoogleToken googleToken = this.googleApiClient.fetchToken(tokenRequestParams(code));
        GoogleMemberResponse googleMemberResponse = googleApiClient.fetchMember("Bearer " + googleToken.accessToken());
        return googleMemberResponse.toDomain();
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
                SocialType.GOOGLE,
                String.valueOf(oauthMember.id()),
                UserRoleEnum.ROLE_GUEST
        );
    }

    private MultiValueMap<String, String> tokenRequestParams(String authCode) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", googleOauthConfig.clientId());
        params.add("client_secret", googleOauthConfig.clientSecret());
        params.add("code", authCode);
        params.add("redirect_uri", googleOauthConfig.redirectUri());
        return params;
    }
}
