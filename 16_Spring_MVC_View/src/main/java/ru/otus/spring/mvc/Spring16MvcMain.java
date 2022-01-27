package ru.otus.spring.mvc;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.mvc.domain.Person;
import ru.otus.spring.mvc.repositories.PersonRepository;

import javax.annotation.PostConstruct;


@SpringBootApplication
public class Spring16MvcMain {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(Spring16MvcMain.class);

    }

}
