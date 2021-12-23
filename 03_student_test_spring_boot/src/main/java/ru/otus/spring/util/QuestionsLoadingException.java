package ru.otus.spring.util;

import java.io.IOException;

public class QuestionsLoadingException extends IOException {

    public QuestionsLoadingException(String message) {
        super(message);
    }
}
