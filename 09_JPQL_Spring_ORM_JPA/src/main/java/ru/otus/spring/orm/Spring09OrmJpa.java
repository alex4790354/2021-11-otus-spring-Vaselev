package ru.otus.spring.orm;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.orm.customExceptions.DaoException;


@SpringBootApplication
public class Spring09OrmJpa {

    public static void main(String[] args) throws DaoException {

        ApplicationContext context = SpringApplication.run(Spring09OrmJpa.class);

    }
}
