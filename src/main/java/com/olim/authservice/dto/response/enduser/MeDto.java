package com.olim.authservice.dto.response.enduser;

import com.olim.authservice.enumeration.UserRoleEnum;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record MeDto (
    @Schema(description = "이메일", example = "pyre@pyre.com")
    String email,
    @Schema(description = "닉네임", example = "pyre")
    String nickname,
    @Schema(description = "프로필 이미지 URL", example = "https://pyre.com/pyre.png")
    String imageUrl,
    @Schema(description = "유저 UUID", example = "asdasf-qweqw-czxc")
    UUID id,
    @Schema(description = "유저 권한", example = "USER_ADMIN")
    UserRoleEnum role
) {


}
