package com.toy.jeongoo.auth.jwt;

import com.toy.jeongoo.auth.jwt.service.JwtService;
import com.toy.jeongoo.user.model.User;
import com.toy.jeongoo.user.service.UserFindService;
import com.toy.jeongoo.utils.DefaultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
@Aspect
@RequiredArgsConstructor
public class JwtAuthAspect {

    private final static String AUTHORIZATION = "Authorization";

    private final static DefaultResponse DEFAULT_RES = DefaultResponse.builder().statusCode(401).message("인증 실패").build();
    private final static ResponseEntity<DefaultResponse> RES_RESPONSE_ENTITY = new ResponseEntity<>(DEFAULT_RES, HttpStatus.UNAUTHORIZED);

    private final HttpServletRequest httpServletRequest;
    private final UserFindService userFindService;
    private final JwtService jwtService;

    //항상 @annotation 패키지 이름을 실제 사용할 annotation 경로로 맞춰줘야 한다.
    @Around("@annotation(com.toy.jeongoo.auth.jwt.JwtAuth)")
    public Object around(final ProceedingJoinPoint pjp) throws Throwable {
        final String jwt = httpServletRequest.getHeader(AUTHORIZATION);
        //토큰 존재 여부 확인
        if (jwt == null) return RES_RESPONSE_ENTITY;
        //토큰 해독
        final JwtService.Token token = jwtService.decode(jwt);
        //토큰 검사
        if (token == null) {
            log.info("토큰 없음");
            return RES_RESPONSE_ENTITY;
        } else {
            final User user = userFindService.findUser(token.getUser_idx());
            log.info("유효 검사");
            //유효 사용자 검사
            if (user == null) return RES_RESPONSE_ENTITY;
            return pjp.proceed(pjp.getArgs());
        }
    }
}


