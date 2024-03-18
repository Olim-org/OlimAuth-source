package com.olim.authservice.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.olim.authservice.enumeration.Gender;
import com.olim.authservice.enumeration.SocialType;
import com.olim.authservice.enumeration.UserRoleEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@Entity
@Getter
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EndUser {


    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "USER_ID", columnDefinition = "BINARY(16)")
    private UUID id;
    @Column
    private String name;
    @Column(unique = true)
    private String phoneNumber;
    @Column
    private String address;
    @Column
    private LocalDate birthDate;
    @Column
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    @Column(unique = true)
    @Pattern(regexp = "^[A-Za-z0-9]{5,12}$", message = "유효하지 않은 닉네임 형식입니다.")
    private String nickname;
    @Column(nullable = false, unique = true, length = 40)
    @Email(message = "유효하지 않은 이메일 형식입니다.", regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$")
    private String email;
    private String password;
    private String profilePictureUrl;
    private String shortDescription;
    @Column(nullable = false)
    private LocalDateTime createDate;

    @Column
    private LocalDateTime modifyDate;
    private LocalDateTime lastActive;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private UserRoleEnum role; // 0 = ROLE_ADMIN, 1 = ROLE_USER

    @Enumerated(EnumType.STRING)
    @Column
    private SocialType socialType;

    @Column
    private String socialId;

    private Boolean agreement1;

    private Boolean agreement2;

    @Builder
    public EndUser(
        String name,
        String phoneNumber,
        String address,
        LocalDate birthDate,
        Gender gender,
        String nickname,
        String email,
        String password,
        String profilePictureUrl,
        String shortDescription,
        Boolean agreement1,
        Boolean agreement2,
        SocialType socialType,
        String socialId,
        UserRoleEnum role
    ) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birthDate = birthDate;
        this.gender = gender;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.profilePictureUrl = profilePictureUrl;
        this.shortDescription = shortDescription;
        this.agreement1 = agreement1;
        this.agreement2 = agreement2;
        this.createDate = LocalDateTime.now();
        this.role = role;
        this.socialId = socialId;
        this.socialType = socialType;
    }
    public static EndUser createUser(
            String name,
            String phoneNumber,
            String address,
            LocalDate birthDate,
            Gender gender,
            String nickname,
            String email,
            String password,
            String profilePictureUrl,
            String shortDescription,
            Boolean agreement1,
            Boolean agreement2,
            SocialType socialType,
            String socialId,
            UserRoleEnum role
    ) {
        EndUser user = EndUser.builder()
                .name(name)
                .phoneNumber(phoneNumber)
                .address(address)
                .birthDate(birthDate)
                .gender(gender)
                .nickname(nickname)
                .email(email)
                .profilePictureUrl(profilePictureUrl)
                .shortDescription(shortDescription)
                .password(password)
                .agreement1(agreement1)
                .agreement2(agreement2)
                .role(role)
                .socialId(socialId)
                .socialType(socialType)
                .build();

        return user;
    }
    public void updatePassword(String password) {
        this.password = password;
    }
}
