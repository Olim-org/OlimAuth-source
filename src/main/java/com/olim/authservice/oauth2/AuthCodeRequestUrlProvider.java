package com.olim.authservice.oauth2;


import com.olim.authservice.enumeration.OAuthServerType;

public interface AuthCodeRequestUrlProvider {

    OAuthServerType supportServer();

    String provide();

}
