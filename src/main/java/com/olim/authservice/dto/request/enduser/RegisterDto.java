package com.olim.authservice.dto.request.enduser;

import com.olim.authservice.enumeration.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public record RegisterDto (
        @NotBlank
        @Schema(description = "이름", example = "홍길동")
        @Pattern(regexp = "^[가-힣]{2,10}$", message = "이름은 2 ~ 10자 이여야 합니다.")
        String name,
        @Pattern(regexp = "^\\d{3}\\d{3,4}\\d{4}$", message = "전화번호 형식이 올바르지 않습니다.")
        @Schema(description = "전화번호", example = "01012345678")
        @NotBlank
        String phoneNumber,
        @Schema(description = "주소", example = "서울특별시 강남구")
        @NotBlank
        String address,
        @NotBlank
        @Schema(description = "생년월일", example = "2000-01-01")
        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "생년월일 형식이 올바르지 않습니다.")
        String birthDate,
        @Schema(description = "성별", example = "FEMALE")
        @NotNull
        Gender gender,
        @Size(min = 5, max = 12, message = "닉네임은 5 ~ 12자 이여야 합니다!")
        @NotBlank
        @Schema(description = "닉네임", example = "pyre")
        @Pattern(regexp = "^[A-Za-z0-9]{5,12}$")
        String nickname,
        @Size(min = 5, max = 40, message = "이메일은 5 ~ 40자 이여야 합니다!")
        @NotBlank
        @Schema(description = "이메일", example = "pyre@pyre.com")
        @Email(message = "유효하지 않은 이메일 형식입니다.", regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$")
        String email,
        @Size(min = 8, max = 40, message = "비밀번호는 8 ~ 40자 이여야 합니다!")
        @Schema(description = "비밀번호", example = "1234")
        @NotBlank
        String password,
        @NotNull
        @Schema(description = "약관 동의1", example = "true")
        Boolean agreement1,
        @Schema(description = "약관 동의2", example = "true")
        Boolean agreement2,
        @Schema(description = "인증번호", example = "12ab34")
        @Pattern(regexp = "^[A-Za-z0-9]{6}$", message = "인증번호 형식이 올바르지 않습니다.")
        @NotBlank
        String authNum
) {



}
