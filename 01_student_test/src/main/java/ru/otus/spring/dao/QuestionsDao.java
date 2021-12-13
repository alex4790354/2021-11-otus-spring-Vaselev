package ru.otus.spring.dao;

import ru.otus.spring.domain.Question;

import java.util.ArrayList;
import java.util.List;

public interface QuestionsDao {

    List<Question> getQuestions();

}
