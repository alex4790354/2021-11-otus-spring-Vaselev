package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.Config;
import ru.otus.spring.dao.interfaces.QuestionsDao;
import ru.otus.spring.service.interfaces.IOService;
import ru.otus.spring.service.interfaces.Localization;
import ru.otus.spring.service.interfaces.QuestionsService;
import ru.otus.spring.domain.Question;
import ru.otus.spring.service.interfaces.StudentLoginService;

import java.util.List;


@Service
public class QuestionsServiceImpl implements QuestionsService {

    private final Config config;
    private final Localization localization;
    private final IOService ioService;
    private final QuestionsDao dao;
    private final StudentLoginService stLoginService;


    @Autowired
    public QuestionsServiceImpl(Config config, Localization localization, IOService ioService, QuestionsDao dao, StudentLoginService stLoginService) {
        this.config = config;
        this.localization = localization;
        this.ioService = ioService;
        this.dao = dao;
        this.stLoginService = stLoginService;
    }


    @Override
    public void takeExam() {
        int correctAnswers = 0;
        String studentAnswer;
        ioService.out(localization.getPropertiesValue("exam.welcome", stLoginService.getStudentName()));
        List<Question> questions = dao.takeExamQuestionsList();
        for (Question question : questions) {
            ioService.out(question.getQuestionText());
            studentAnswer = ioService.readString();
            if (question.getAnswer().equals(studentAnswer)) {
                correctAnswers++;
            }
        }
        if (correctAnswers >= config.getCorrectAnswersToPass()) {
            ioService.out(localization.getPropertiesValue("exam.pass"));
        } else {
            ioService.out(localization.getPropertiesValue("exam.fail"));
        }
    }

}
