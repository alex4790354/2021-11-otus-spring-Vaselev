package ru.otus.jpql;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.jpql.customExceptions.DaoException;


@SpringBootApplication
public class JpqlDemoApplication {

    public static void main(String[] args) throws DaoException { //throws Exception {

        ApplicationContext context = SpringApplication.run(JpqlDemoApplication.class);

    }
}
