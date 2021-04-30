package com.toy.jeongoo.auth.jwt.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@RequiredArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "jwt.secret")
public class JwtProperties {

    private final String key;
    private final long validTime;
    private final long serialVersionUUID;
}
