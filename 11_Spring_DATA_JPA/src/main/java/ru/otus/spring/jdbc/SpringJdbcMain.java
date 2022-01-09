package ru.otus.spring.jdbc;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.jdbc.customExceptions.DaoException;


@SpringBootApplication
public class SpringJdbcMain {

    public static void main(String[] args) throws DaoException { //throws Exception {

        ApplicationContext context = SpringApplication.run(SpringJdbcMain.class);

    }
}
