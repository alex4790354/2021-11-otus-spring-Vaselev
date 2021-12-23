package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.util.QuestionsLoadingException;
import ru.otus.spring.service.QuestionsService;


@SpringBootApplication
public class Application {

	public static void main(String[] args) throws QuestionsLoadingException {
		ApplicationContext context = SpringApplication.run(Application.class, args);

		QuestionsService questionsService = context.getBean(QuestionsService.class);
		questionsService.takeExam();

	}

}
