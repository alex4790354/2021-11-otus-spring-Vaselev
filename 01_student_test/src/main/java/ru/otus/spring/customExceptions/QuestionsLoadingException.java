package ru.otus.spring.customExceptions;

import java.io.FileNotFoundException;
import java.io.IOException;

public class QuestionsLoadingException extends IOException {

    public QuestionsLoadingException(String message) {
        super(message);
    }
}
