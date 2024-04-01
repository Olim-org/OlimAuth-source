## 🙌 올림 프로젝트 CRM

<img src="https://cdn.discordapp.com/attachments/393025698907947009/1219293363501535363/2023-12-26_143330.png?ex=660ac66f&is=65f8516f&hm=99f744e1e9d97969c392296d1fa1eca9ba2ea903d8df684fd901620e6dfcc191&" alt="drawing" width="400"/>

#### 올림 프로젝트는 개발 중단된 프로젝트입니다. 아래 웹 사이트를 통해 테스트할 수 있습니다.
#### [Github 조직](https://github.com/Olim-org) 
#### [Website](https://olim-crm-front.vercel.app/introduction)

## 기술 스택
- Java 21
- Spring Boot (3.2.2)
- Spring Web
- Spring Data JPA
- Spring Cloud eureka client
- Spring Cloud config
- Spring cloud Open Feign

- Jwt
- Spring Boot Security
- Redis
- Mysql
- AWS S3

## 인증 서비스
- 유저의 회원가입, 로그인 등 인증 및 인가를 관리합니다.
- JWT (access token + refresh token) 발급 및 Redis를 통해 refresh를 저장합니다.
- 저장된 리프레시 토큰은 1번 사용되면 다시 재발급 + 재저장 됩니다.
- 이미지 업로드를 위한 S3 업로드 서비스가 포함되어 있습니다.
- 유저 프로필 관리 및 이메일 서비스도 함께 포함되어 있습니다.
- Swagger API [링크](https://apis.olim.pyre.live/auth-service/swagger-ui/index.html)

## 🔅 프로젝트 구조

<img src="https://cdn.discordapp.com/attachments/393025698907947009/1224267515824439376/image.png?ex=661cdef9&is=660a69f9&hm=f680237181f7a0a4d720b72d53e79ff67361f3588d72a939b5c077dee9ded09f&" alt="drawing" width="600"/>

