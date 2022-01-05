package ru.otus.spring.dao.interfaces;


import ru.otus.spring.domain.Question;

import java.util.List;

public interface QuestionsDao {

    List<Question> takeExamQuestionsList();
}
