package com.olim.authservice.oauth2.naver;



import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.olim.authservice.entity.OauthMember;
import com.olim.authservice.enumeration.OAuthServerType;
import com.olim.authservice.oauth2.OauthId;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@JsonNaming(value = SnakeCaseStrategy.class)
public record NaverMemberResponse(
        String resultcode,
        String message,
        Response response
) {

    public OauthMember toDomain() {
        return OauthMember.createOauthMember(
                new OauthId(String.valueOf(response.id), OAuthServerType.NAVER),
                response.nickname,
                response.profileImage,
                response.email,
                response.name
        );
    }


    @JsonNaming(value = SnakeCaseStrategy.class)
    public record Response(
            String id,
            String nickname,
            String name,
            String email,
            String gender,
            String age,
            String birthday,
            String profileImage,
            String birthyear,
            String mobile
    ) {
    }
}

