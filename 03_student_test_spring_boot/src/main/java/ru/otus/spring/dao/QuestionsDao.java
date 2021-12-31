package ru.otus.spring.dao;


import ru.otus.spring.util.QuestionsLoadingException;
import ru.otus.spring.domain.Question;
import java.util.List;


public interface QuestionsDao {

    List<Question> takeExamQuestionsList(String fileName) throws QuestionsLoadingException;

}
