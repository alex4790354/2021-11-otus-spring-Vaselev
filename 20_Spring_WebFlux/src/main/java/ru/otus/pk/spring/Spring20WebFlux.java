package ru.otus.pk.spring;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableMongock
@SpringBootApplication
@EnableConfigurationProperties
public class Spring20WebFlux {

    public static void main(String[] args) {
        SpringApplication.run(Spring20WebFlux.class, args);
    }
}
