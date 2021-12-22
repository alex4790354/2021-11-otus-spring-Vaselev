package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.Util.ExamConfig;


@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);

		ExamConfig examConfig = context.getBean(ExamConfig.class);
		System.out.println(examConfig.getCorrectAnswersToPass());

	}

}
