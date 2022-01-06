package ru.otus.spring;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.customExceptions.DaoException;


@SpringBootApplication
public class Main {

    public static void main(String[] args) throws DaoException { //throws Exception {

        ApplicationContext context = SpringApplication.run(Main.class);

    }
}
