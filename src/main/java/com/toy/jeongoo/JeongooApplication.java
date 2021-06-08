package com.toy.jeongoo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityManager;

@SpringBootApplication
@EnableJpaAuditing
public class JeongooApplication {

    public static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:application.yml,"
            + "/app/config/geongoo/real-application.yml";

    public static void main(String[] args) {
        SpringApplication.run(JeongooApplication.class);

//        new SpringApplicationBuilder(Application.class)
//                .properties(APPLICATION_LOCATIONS)
//                .run(args);
    }

    @Bean
    JPAQueryFactory jpaQueryFactory(EntityManager em) {
        return new JPAQueryFactory(em);
    }
}
