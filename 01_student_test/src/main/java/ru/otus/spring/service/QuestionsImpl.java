package ru.otus.spring.service;

import ru.otus.spring.dao.QuestionsDao;
import ru.otus.spring.domain.Question;

import java.util.ArrayList;

public class QuestionsImpl implements QuestionsService {

    private final QuestionsDao dao;

    public QuestionsImpl(QuestionsDao dao) {
        this.dao = dao;
    }

    @Override
    public ArrayList<Question> getQuestions() {
        return (ArrayList<Question>) dao.getQuestions();
    }
}
