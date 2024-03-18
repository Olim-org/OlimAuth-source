package com.olim.authservice.dto.response.enduser;

import com.olim.authservice.entity.EndUser;
import com.olim.authservice.enumeration.Gender;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


public record UserInfoFeignResponse(
        UUID id,
        String name,
        String phoneNumber,
        String address,
        LocalDate birthDate,
        Gender gender,
        String email,
        String nickname,
        String role
) {
    public static UserInfoFeignResponse createDto(EndUser user) {
        UserInfoFeignResponse dto = new UserInfoFeignResponse(
                user.getId(),
                user.getName(),
                user.getPhoneNumber(),
                user.getAddress(),
                user.getBirthDate(),
                user.getGender(),
                user.getEmail(),
                user.getNickname(),
                user.getRole().getKey()
        );
        return dto;
    }
}
