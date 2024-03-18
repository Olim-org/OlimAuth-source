package com.olim.authservice.oauth2;


import com.olim.authservice.enumeration.OAuthServerType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;
import static lombok.AccessLevel.PROTECTED;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class OauthId {

    @Column(nullable = false, name = "oauth_server_id")
    private String oauthServerId;

    @Enumerated(STRING)
    @Column(nullable = false, name = "oauth_server")
    private OAuthServerType oauthServerType;

    public String oauthServerId() {
        return oauthServerId;
    }

    public OAuthServerType oauthServer() {
        return oauthServerType;
    }
}

