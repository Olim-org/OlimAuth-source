package com.olim.authservice.enumeration;

import static java.util.Locale.ENGLISH;

public enum OAuthServerType {

    KAKAO,
    NAVER,
    GOOGLE
    ;

    public static OAuthServerType fromName(String type) {
        return OAuthServerType.valueOf(type.toUpperCase(ENGLISH));
    }
}

