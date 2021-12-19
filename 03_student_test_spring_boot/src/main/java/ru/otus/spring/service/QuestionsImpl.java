package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.QuestionsDao;
import ru.otus.spring.domain.Question;

import java.util.ArrayList;

@Service
public class QuestionsImpl implements QuestionsService {

    private final QuestionsDao dao;

    @Autowired
    public QuestionsImpl(QuestionsDao dao) {
        this.dao = dao;
    }

}
