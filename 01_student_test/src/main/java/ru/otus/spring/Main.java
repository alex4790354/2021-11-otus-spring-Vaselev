package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.dao.QuestionsDao;
import ru.otus.spring.dao.QuestionsFromResFile;
import ru.otus.spring.domain.Question;
import ru.otus.spring.service.QuestionsService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        //QuestionsFromResFile questionsFromResFile = new QuestionsFromResFile("csv/test-quastions.csv");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

        // Should get service but getting an Error:  No default constructor found;
        //QuestionsService service = context.getBean(QuestionsService.class);
        //ArrayList<Question> questions = service.getQuestions();

        QuestionsDao questionsDao = context.getBean(QuestionsDao.class);
        List<Question> questions = questionsDao.getQuestions();

        for (Question question : questions) {
            System.out.println(question);
        }

    }


}
