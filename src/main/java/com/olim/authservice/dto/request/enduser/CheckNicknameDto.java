package com.olim.authservice.dto.request.enduser;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;

public record CheckNicknameDto(
        @Schema(description = "닉네임", example = "pyre")
        @Pattern(regexp = "^[A-Za-z0-9]{5,12}$", message = "유효하지 않은 닉네임 형식입니다.")
        String nickname
) {
}
