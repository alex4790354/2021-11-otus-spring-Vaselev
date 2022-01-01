package ru.otus.spring.service.interfaces;


import ru.otus.spring.util.QuestionsLoadingException;

import java.io.IOException;

public interface QuestionsService {
    void takeExam() throws IOException;
}
