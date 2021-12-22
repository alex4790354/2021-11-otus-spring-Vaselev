package ru.otus.spring.service;


import ru.otus.spring.Util.QuestionsLoadingException;

public interface QuestionsService {
    void takeExam()  throws QuestionsLoadingException;
}
