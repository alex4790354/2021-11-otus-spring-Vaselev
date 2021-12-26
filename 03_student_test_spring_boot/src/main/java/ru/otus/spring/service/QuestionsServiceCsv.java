package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.ExamConfig;
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

    @Autowired
    public QuestionsServiceCsv(QuestionsDao dao, ExamConfig examConfig) throws QuestionsLoadingException {
        this.dao = dao;
        this.config = examConfig;
        takeExam();
    }

    @Override
    public void takeExam() throws QuestionsLoadingException {
        int correctAnswers = 0;
        String studentAnswer;

        Util.SendMessage("Screen", config.getExamPropertiesValue(null, "exam.wellcome"));
        List<Question> questions = dao.takeExamQuestionsList();
        for (Question question : questions) {
            Util.SendMessage("Screen", question.getQuestionText());
            studentAnswer = Util.ReadMessage("Screen");
            if (question.getAnswer().equals(studentAnswer)) {
                correctAnswers++;
            }
        }
        if (correctAnswers >= config.getCorrectAnswersToPass()) {
            Util.SendMessage("Screen", config.getExamPropertiesValue(null, "exam.pass"));
        } else {
            Util.SendMessage("Screen", config.getExamPropertiesValue(null, "exam.fail"));
        }
    }

}
