package com.toy.jeongoo.auth.jwt;

import com.toy.jeongoo.auth.jwt.properties.JwtProperties;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;
    private final UserDetailsService userDetailsService;

    public String createToken(String userPK) {
        Claims claims = Jwts.claims().setSubject(userPK);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + jwtProperties.getValidTime()))
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getKey())
                .compact();
    }

    public Authentication getAuthentication(String token) {
        final UserDetails userDetails = userDetailsService.loadUserByUsername(findUserPK(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String findUserPK(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getKey())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String decodeToken(HttpServletRequest request) {
        final String requestTokenHeader = request.getHeader("Authorization");
        String token = null;
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer")) {
            token = requestTokenHeader.substring(7);
        }
        return token;
    }

    public boolean isValidate(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(jwtProperties.getKey()).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            return false;
        }
    }
}
