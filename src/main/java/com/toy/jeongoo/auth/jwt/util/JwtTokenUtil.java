package com.toy.jeongoo.auth.jwt.util;

import com.toy.jeongoo.auth.jwt.properties.JwtProperties;
import com.toy.jeongoo.user.api.dto.request.SignInRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

    private final JwtProperties jwtProperties;

    public String getEmailByToken(String token) {
        return getClaimByToken(token, Claims::getSubject);
    }

    public Date getExpirationDate(String token) {
        return getClaimByToken(token, Claims::getExpiration);
    }

    public String generate(SignInRequest signInRequest) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, signInRequest.getEmail());
    }

    public Boolean isValidate(String token, SignInRequest signInRequest){
        final String email = getEmailByToken(token);
        return email.equals(signInRequest.getEmail()) && !isExpired(token);
    }

    private <T> T getClaimByToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsByToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsByToken(String token) {
        return Jwts.parser().setSigningKey(jwtProperties.getKey()).parseClaimsJws(token).getBody();
    }

    private Boolean isExpired(String token) {
        final Date expiration = getExpirationDate(token);
        return expiration.before(new Date());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getValidTime()))
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getKey())
                .compact();
    }
}
