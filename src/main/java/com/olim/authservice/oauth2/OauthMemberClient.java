package com.olim.authservice.oauth2;


import com.olim.authservice.entity.EndUser;
import com.olim.authservice.entity.OauthMember;
import com.olim.authservice.enumeration.OAuthServerType;

public interface OauthMemberClient {

    OAuthServerType supportServer();

    OauthMember fetch(String code);

    EndUser fetchEnduser(OAuthServerType oAuthServerType, OauthMember oauthMember);
}
