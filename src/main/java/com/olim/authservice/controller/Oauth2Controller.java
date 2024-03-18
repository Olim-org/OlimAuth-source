package com.olim.authservice.controller;


import com.olim.authservice.dto.request.oauth.OauthDto;
import com.olim.authservice.dto.response.enduser.JwtDto;
import com.olim.authservice.enumeration.OAuthServerType;
import com.olim.authservice.service.Oauth2Service;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/auth-service/oauth")
@Tag(name="OAuth2", description = "OAUTH2 API 구성")
@Validated
public class Oauth2Controller {
    private final Oauth2Service oauth2Service;

    @SneakyThrows
    @GetMapping("/{oauthServerType}")
    public ResponseEntity<Void> redirectAuthCodeRequestUrl(
            @PathVariable OAuthServerType oauthServerType,
            HttpServletResponse response
    ) {
        String redirectUrl = this.oauth2Service.getAuthCodeRequestUrl(oauthServerType);
        response.sendRedirect(redirectUrl);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/login/{oauthServerType}")
    public ResponseEntity<JwtDto> login(
            @PathVariable OAuthServerType oauthServerType,
            @RequestBody OauthDto code,
            HttpServletResponse response,
            HttpServletRequest request
    ) {
        JwtDto login = this.oauth2Service.login(oauthServerType, code.code(), response, request.getRemoteAddr());
        return ResponseEntity.ok(login);
    }

}
