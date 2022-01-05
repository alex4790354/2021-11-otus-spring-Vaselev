package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.Config;
import ru.otus.spring.dao.interfaces.QuestionsDao;
import ru.otus.spring.service.interfaces.IOService;
import ru.otus.spring.service.interfaces.Localization;
import ru.otus.spring.service.interfaces.QuestionsService;
import ru.otus.spring.domain.Question;

import java.util.List;


@Service
public class QuestionsServiceImpl implements QuestionsService {

    private final Config config;
    private final Localization localization;
    private final IOService ioService;
    private final QuestionsDao dao;

    @Autowired
    public QuestionsServiceImpl(Config config, QuestionsDao dao, Localization localization, IOService ioService) {
        this.config = config;
        this.dao = dao;
        this.localization = localization;
        this.ioService = ioService;
    }

    @Override
    public void takeExam() {
        int correctAnswers = 0;
        String studentAnswer;
        ioService.out(localization.getPropertiesValue("exam.ask-name", config.getLocaleLanguage(), config.getLocaleCountry()));
        String userName = ioService.readString();
        ioService.out(localization.getPropertiesValue("exam.welcome", config.getLocaleLanguage(), config.getLocaleCountry(), userName));
        List<Question> questions = dao.takeExamQuestionsList();
        for (Question question : questions) {
            ioService.out(question.getQuestionText());
            studentAnswer = ioService.readString();
            if (question.getAnswer().equals(studentAnswer)) {
                correctAnswers++;
            }
        }
        if (correctAnswers >= config.getCorrectAnswersToPass()) {
            ioService.out(localization.getPropertiesValue("exam.pass", config.getLocaleLanguage(), config.getLocaleCountry()));
        } else {
            ioService.out(localization.getPropertiesValue("exam.fail", config.getLocaleLanguage(), config.getLocaleCountry()));
        }
    }

}
