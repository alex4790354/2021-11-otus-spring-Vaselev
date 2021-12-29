package ru.otus.spring.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.otus.spring.util.QuestionsLoadingException;

@Service
public class MainService {

    private QuestionsServiceCsv questionsServiceCsv;

    public MainService(QuestionsServiceCsv questionsServiceCsv) throws QuestionsLoadingException {
        this.questionsServiceCsv = questionsServiceCsv;

        this.questionsServiceCsv.takeExam();
    }
}
