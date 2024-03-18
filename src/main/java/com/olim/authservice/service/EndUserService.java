package com.olim.authservice.service;



import com.olim.authservice.dto.request.enduser.LoginDto;
import com.olim.authservice.dto.request.enduser.RegisterDto;
import com.olim.authservice.dto.request.enduser.ResetPasswordRequest;
import com.olim.authservice.dto.response.enduser.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.transaction.annotation.Transactional;


public interface EndUserService {

    @Transactional
    JwtDto register(RegisterDto registerDto, String ip, HttpServletResponse response);
    @Transactional
    JwtDto login(LoginDto loginDto, HttpServletResponse response, String ip);
    @Transactional
    String findPassword(String email);
    @Transactional
    String resetPassword(ResetPasswordRequest resetPasswordRequest, String token);
    @Transactional
    CheckEmailViewDto valEmail(String email);
    @Transactional
    CheckEmailViewDto valUsername(String username);
    @Transactional
    MeDto me(HttpServletRequest request);
    @Transactional
    JwtDto refreshPage(String refresh_token, HttpServletResponse response, String ip);
    @Transactional
    void logout(String refresh_token, HttpServletResponse response);
    CheckPhoneResponse valPhone(String phone);
    @Transactional
    UserInfoFeignResponse getUserInfo(String token);

}
