package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.service.QuestionsServiceCsv;
import ru.otus.spring.util.QuestionsLoadingException;

import java.io.IOException;


@SpringBootApplication
public class Application {

	public static void main(String[] args) throws IOException {
		ApplicationContext context = SpringApplication.run(Application.class, args);

		QuestionsServiceCsv questionsServiceCsv = context.getBean(QuestionsServiceCsv.class);
		questionsServiceCsv.takeExam();

	}

}
