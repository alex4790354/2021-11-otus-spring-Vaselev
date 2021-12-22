package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.Util.ExamConfig;
import ru.otus.spring.Util.QuestionsLoadingException;
import ru.otus.spring.Util.Util;
import ru.otus.spring.dao.QuestionsDao;
import ru.otus.spring.domain.Question;

import java.util.List;


@Service
public class QuestionsImpl implements QuestionsService {

    private final QuestionsDao dao;
    private final Util util;
    private final ExamConfig examConfig;

    @Autowired
    public QuestionsImpl(QuestionsDao dao, Util util, ExamConfig examConfig) {
        this.dao = dao;
        this.util = util;
        this.examConfig = examConfig;
    }

    @Override
    public void takeExam() throws QuestionsLoadingException {
        int correctAnswers = 0;
        String studentAnswer;

        List<Question> questions = dao.takeExam();
        for (Question question : questions) {
            this.util.SendMessage("Screen", question.getQuestionText());
            studentAnswer = this.util.ReadMessage("Screen");
            if (question.getAnswer().equals(studentAnswer)) {
                correctAnswers++;
            }
        }
        if (correctAnswers >= examConfig.getCorrectAnswersToPass()) {
            this.util.SendMessage("Screen", "You pass");
        } else {
            this.util.SendMessage("Screen", "You didn't pass");
        }
    }

}
