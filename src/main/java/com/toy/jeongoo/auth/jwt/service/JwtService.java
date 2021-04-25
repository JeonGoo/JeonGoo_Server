package com.toy.jeongoo.auth.jwt.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Slf4j
@Service
public class JwtService {

    @Value("${JWT.ISSUER}")
    private String ISSUER;

    @Value("${JWT.SECRET}")
    private String SECRET;


    public String create(final long id) {
        try {
            //토큰 생성 빌더 객체 생성
            JWTCreator.Builder b = JWT.create();
            //토큰 생성자 명시
            b.withIssuer(ISSUER);
            //토큰 payload 작성, key - value 형식, 객체도 가능
            b.withClaim("id", id);
            //토큰 해싱해서 반환
            return b.sign(Algorithm.HMAC256(SECRET));
        } catch (JWTCreationException JwtCreationException) {
            log.info(JwtCreationException.getMessage());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Token decode(final String token) {
        try {
            //토큰 해독 객체 생성
            final JWTVerifier jwtVerifier = require(Algorithm.HMAC256(SECRET)).withIssuer(ISSUER).build();
            if (jwtVerifier == null) {
                log.info("베리파이어가 널");
            }
            log.info("베리파이어 생성");
            //토큰 검증
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            if (decodedJWT == null) {
                log.info("해독된게 널");
            }
            log.info("해독된거 생성");
            //토큰 payload 반환, 정상적인 토큰이라면 토큰 주인(사용자) 고유 id, 아니라면 -1
            Token idToken = new Token(decodedJWT.getClaim("id").asLong().intValue());
            if (idToken == null) {
                log.info("만들어진 토큰이 이상한데? : " + idToken.getId());
            }
            log.info("토큰번호 : " + idToken.getId());
            return idToken;
        } catch (JWTVerificationException jve) {
            log.error(jve.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new Token();
    }

    public static class Token {
        //토큰에 담길 정보 필드
        //초기값을 -1로 설정함으로써 로그인 실패시 -1반환
        private long id = -1;

        public Token() {
        }

        public Token(final int id) {
            log.info("토큰생성시도 : " + id);
            this.id = id;
        }

        public long getId() {
            return this.id;
        }
    }

    //반환될 토큰Res
    public static class TokenRes {
        //실제 토큰
        private String token;

        public TokenRes() {
        }

        public TokenRes(final String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
