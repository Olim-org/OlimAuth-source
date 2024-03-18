package com.olim.authservice.dto.request.enduser;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public record LoginDto (
        @Size(min = 5, max = 40, message = "이메일은 5 ~ 40자 이여야 합니다!")
        @NotBlank
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "이메일 형식이 올바르지 않습니다!")
        @Email
        @Schema(description = "이메일", example = "pyre@pyre.com")
        String email,

        @Size(min = 8, max = 40, message = "비밀번호는 8 ~ 40자 이여야 합니다!")
        @NotBlank
        @Schema(description = "비밀번호", example = "1234")
        String password
) {


}
