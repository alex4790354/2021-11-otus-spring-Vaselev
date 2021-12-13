package ru.otus.spring.service;

import ru.otus.spring.dao.QuestionsDao;
import ru.otus.spring.domain.Question;
import java.util.List;

public class QuestionsImpl implements QuestionsService {

    private final QuestionsDao dao;
    public QuestionsImpl(QuestionsDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Question> getQuestions() {
        return dao.getQuestions();
    }
}
