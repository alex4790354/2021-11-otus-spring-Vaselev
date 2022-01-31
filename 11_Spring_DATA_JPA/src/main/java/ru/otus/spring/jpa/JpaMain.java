package ru.otus.spring.jpa;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.jpa.customExceptions.DaoException;


@SpringBootApplication
public class JpaMain {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(JpaMain.class);

    }
}