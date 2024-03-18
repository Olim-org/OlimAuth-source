package com.olim.authservice.dto.response.enduser;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record JwtDto (
    @Schema(description = "액세스 토큰", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJweXJlQHB5cmUuY29tIiwiaWF0IjoxNjM0NzIwNzIwLCJleHAiOjE2MzQ3MjA3MjaaaB9.")
    String accessToken
) {

}
