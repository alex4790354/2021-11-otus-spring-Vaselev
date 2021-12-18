package ru.otus.spring.myExceptions;

import java.io.IOException;

public class QuestionsLoadingException extends IOException {

    public QuestionsLoadingException(String message) {
        super(message);
    }
}
