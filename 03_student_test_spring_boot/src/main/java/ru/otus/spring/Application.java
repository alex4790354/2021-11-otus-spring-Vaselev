package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.spring.domain.Question;
import ru.otus.spring.service.QuestionsService;

import java.util.ArrayList;
import java.util.Scanner;

@SpringBootApplication
public class Application {

	public static String locale = "ru_ru";

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);

		//AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
		QuestionsService service = context.getBean(QuestionsService.class);
		ArrayList<Question> questions = service.getQuestions();

		Scanner console = new Scanner(System.in);
		String input;
		int correctAnswersNumber  = 0;

		for (Question question : questions) {
			System.out.println(question);
			input = console.nextLine();
			if (question.getAnswer().equals(input)) {
				correctAnswersNumber++;
			}
			System.out.println("You score now: " + correctAnswersNumber );
		}
		//context.close();

		System.out.println("Your total rate is: " + correctAnswersNumber * 100 / questions.size() + "%" );

	}

}
