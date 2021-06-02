package com.toy.jeongoo.config;

import com.toy.jeongoo.auth.jwt.properties.JwtProperties;
import com.toy.jeongoo.file.properties.AwsS3Properties;
import com.toy.jeongoo.payment.properties.PaymentProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({JwtProperties.class, AwsS3Properties.class, PaymentProperties.class})
public class PropertiesConfig {
}
