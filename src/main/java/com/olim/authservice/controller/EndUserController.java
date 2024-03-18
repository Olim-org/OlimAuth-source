package com.olim.authservice.controller;

import com.olim.authservice.dto.request.enduser.*;
import com.olim.authservice.dto.response.enduser.CheckPhoneResponse;
import com.olim.authservice.service.EndUserService;
import com.olim.authservice.dto.response.enduser.CheckEmailViewDto;
import com.olim.authservice.dto.response.enduser.JwtDto;
import com.olim.authservice.dto.response.enduser.UserInfoFeignResponse;
import com.olim.authservice.exception.customexception.VerifyEmailFailException;
import com.olim.authservice.service.EndUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/auth-service/user")
@Tag(name="EndUser", description = "EndUser API 구성")
@Validated
public class EndUserController {
    private final EndUserService endUserService;

    @Operation(description = "회원가입")
    @PostMapping("/register")
    public ResponseEntity<JwtDto> register(@RequestBody @Valid RegisterDto registerDto, HttpServletRequest request, HttpServletResponse response) {

        return new ResponseEntity<>(this.endUserService.register(registerDto, request.getRemoteAddr(), response), HttpStatus.OK);

    }

    @Operation(description = "로그인")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDto loginDto, HttpServletResponse httpServletResponse, HttpServletRequest request) {
        try {
            return new ResponseEntity<>( this.endUserService.login(loginDto, httpServletResponse, request.getRemoteAddr()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("이메일과 비밀번호를 확인바랍니다.", HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(description = "비밀번호 찾기")
    @PostMapping("/find/password")
    public ResponseEntity<?> findPassword(@RequestBody FindPasswordRequest findPasswordRequest) {
        return new ResponseEntity<>(this.endUserService.findPassword(findPasswordRequest.email()), HttpStatus.OK);
    }
    @Operation(description = "비밀번호 초기화 링크")
    @PostMapping("/resetPassword/{token}")
    public ResponseEntity<?> resetPassword(
            @RequestBody ResetPasswordRequest resetPasswordRequest,
            @PathVariable("token") String token
    ) {
        return new ResponseEntity<>(this.endUserService.resetPassword(resetPasswordRequest, token), HttpStatus.OK);
    }
    @Operation(description = "이메일 중복 확인")
    @GetMapping("/check/email")
    public ResponseEntity<CheckEmailViewDto> valEmail(@RequestParam("email") String email) {
        CheckEmailViewDto checkEmailViewDto = this.endUserService.valEmail(email);
        return new ResponseEntity<>(checkEmailViewDto, HttpStatus.OK);
    }

    @Operation(description = "닉네임 중복 확인")
    @GetMapping("/check/nickname")
    public ResponseEntity<CheckEmailViewDto> valUsername(@RequestParam("nickname") String nickname) {
        CheckEmailViewDto checkEmailViewDto = this.endUserService.valUsername(nickname);
        return new ResponseEntity<>(checkEmailViewDto, HttpStatus.OK);
    }

    @Operation(description = "유저 확인")
    @GetMapping("/me")
    public ResponseEntity<?> me(HttpServletRequest request) {
        try {
            return new ResponseEntity<>(this.endUserService.me(request), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }

    }

    @Operation(description = "리프레시")
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshPage(@CookieValue(value = "refresh_token", required = false) Cookie cookie, HttpServletResponse response, HttpServletRequest request) {
        if (cookie == null) {
            throw new VerifyEmailFailException("리프레시 토큰이 없습니다.");
        }
        String refreshToken = cookie.getValue();
        if (refreshToken == null) {
            throw new VerifyEmailFailException("리프레시 토큰이 없습니다.");
        }

        return new ResponseEntity<>(this.endUserService.refreshPage(refreshToken, response, request.getRemoteAddr()), HttpStatus.OK);
    }

    @Operation(description = "로그아웃")
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                log.error(cookie.getValue());
                if ("refresh_token".equals(cookie.getName())) {
                    refreshToken = cookie.getValue();
                    break;
                }
            }
        }

        this.endUserService.logout(refreshToken, response);
        return new ResponseEntity<>("로그아웃 되었습니다.", HttpStatus.OK);
    }
    @Operation(description = "휴대폰 번호 중복 확인")
    @GetMapping("/check/phoneNumber")
    @Parameters({
            @Parameter(name = "phoneNumber", description = "휴대폰 번호", required = true)
    })
    public ResponseEntity<CheckPhoneResponse> valPhoneNumber(@RequestParam String phoneNumber) {
        CheckPhoneResponse checkPhoneResponse = this.endUserService.valPhone(phoneNumber);
        return new ResponseEntity<>(checkPhoneResponse, HttpStatus.OK);
    }
    @Operation(description = "유저 정보 가져오기 Feign 클라이언트 용")
    @GetMapping("/info")
    public UserInfoFeignResponse getUserInfo(@RequestHeader("Authorization") String token) {
        return this.endUserService.getUserInfo(token);
    }
}
