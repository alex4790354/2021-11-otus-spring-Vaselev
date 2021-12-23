package ru.otus.spring.service;


import ru.otus.spring.util.QuestionsLoadingException;

public interface QuestionsService {
    void takeExam()  throws QuestionsLoadingException;
}
