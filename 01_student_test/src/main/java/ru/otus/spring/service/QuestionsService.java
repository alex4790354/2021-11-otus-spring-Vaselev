package ru.otus.spring.service;

import ru.otus.spring.domain.Question;

import java.util.ArrayList;

public interface QuestionsService {

    ArrayList<Question> getQuestions();

}
