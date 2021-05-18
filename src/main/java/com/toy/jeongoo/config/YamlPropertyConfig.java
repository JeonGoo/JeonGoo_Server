package com.toy.jeongoo.config;

import com.toy.jeongoo.factory.YamlPropertySourceFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:jwt.yml", "classpath:aws.yml"},
        factory = YamlPropertySourceFactory.class, ignoreResourceNotFound = true)
public class YamlPropertyConfig {
}
