package ru.otus.pk.spring;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableMongock
@SpringBootApplication
@EnableConfigurationProperties
public class WebFluxMain {

    public static void main(String[] args) {
        SpringApplication.run(WebFluxMain.class, args);
    }
}
