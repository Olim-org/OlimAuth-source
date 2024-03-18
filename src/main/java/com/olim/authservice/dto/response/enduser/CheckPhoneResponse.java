package com.olim.authservice.dto.response.enduser;

import io.swagger.v3.oas.annotations.media.Schema;

public record CheckPhoneResponse(
        @Schema(description = "메시지", example = "사용 가능한 이메일입니다.")
        String message,
        @Schema(description = "휴대폰 중복 여부", example = "true")
        Boolean present
) {
}
