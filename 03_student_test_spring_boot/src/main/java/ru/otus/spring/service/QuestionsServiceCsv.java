package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.ExamConfig;
import ru.otus.spring.service.interfaces.IOService;
import ru.otus.spring.service.interfaces.QuestionsService;
import ru.otus.spring.util.QuestionsLoadingException;
import ru.otus.spring.util.Util;
import ru.otus.spring.dao.QuestionsDao;
import ru.otus.spring.domain.Question;
import java.util.List;


@Service
public class QuestionsServiceCsv implements QuestionsService {

    private final ExamConfig config;
    private final QuestionsDao dao;
    private final Util util;
    private final IOService ioService;

    @Autowired
    public QuestionsServiceCsv(ExamConfig config, QuestionsDao dao, Util util, IOService ioService) {
        this.config = config;
        this.dao = dao;
        this.util = util;
        this.ioService = ioService;
    }

    @Override
    public void takeExam() throws QuestionsLoadingException {
        int correctAnswers = 0;
        String studentAnswer;
        System.out.println("file-name: " + config.getExamFileNameCsv());
        ioService.out(util.getExamPropertiesValue(null, "exam.ask-name"));
        String userName = ioService.readString();
        ioService.out(util.getExamPropertiesValue(new Object[] {userName}, "exam.welcome"));
        List<Question> questions = dao.takeExamQuestionsList(config.getExamFileNameCsv());
        for (Question question : questions) {
            ioService.out(question.getQuestionText());
            studentAnswer = ioService.readString();
            if (question.getAnswer().equals(studentAnswer)) {
                correctAnswers++;
            }
        }
        if (correctAnswers >= config.getCorrectAnswersToPass()) {
            ioService.out(util.getExamPropertiesValue(null, "exam.pass"));
        } else {
            ioService.out(util.getExamPropertiesValue(null, "exam.fail"));
        }
    }

}
