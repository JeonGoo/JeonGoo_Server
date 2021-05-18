package com.toy.jeongoo.config;

import com.toy.jeongoo.auth.jwt.properties.JwtProperties;
import com.toy.jeongoo.file.properties.AwsS3Properties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({JwtProperties.class, AwsS3Properties.class})
public class PropertiesConfig {
}
