package ru.otus.spring.dao;


import ru.otus.spring.Util.QuestionsLoadingException;
import ru.otus.spring.domain.Question;
import java.util.List;


public interface QuestionsDao {

    List<Question> takeExam() throws QuestionsLoadingException;

}
