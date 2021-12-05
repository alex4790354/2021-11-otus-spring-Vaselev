package ru.otus.spring.dao;

import ru.otus.spring.domain.Question;

import java.util.ArrayList;

public interface QuestionsDao {

    public ArrayList<Question> getQuestions();

}
