package ru.otus.spring.service.interfaces;


import ru.otus.spring.util.QuestionsLoadingException;

public interface QuestionsService {
    void takeExam()  throws QuestionsLoadingException;
}
