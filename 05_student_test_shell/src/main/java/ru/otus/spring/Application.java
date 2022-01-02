package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.service.interfaces.QuestionsService;


import java.io.IOException;


@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);
		QuestionsService questionsServiceCsv = context.getBean(QuestionsService.class);
		questionsServiceCsv.takeExam();
	}

}
