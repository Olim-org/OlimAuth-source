package com.olim.authservice.oauth2;


import com.olim.authservice.enumeration.OAuthServerType;
import org.springframework.core.convert.converter.Converter;

public class OauthServerTypeConverter implements Converter<String, OAuthServerType> {

    @Override
    public OAuthServerType convert(String source) {
        return OAuthServerType.fromName(source);
    }

}
