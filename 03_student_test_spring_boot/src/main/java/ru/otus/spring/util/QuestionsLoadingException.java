package ru.otus.spring.util;

import java.io.FileNotFoundException;
import java.io.IOException;

public class QuestionsLoadingException extends FileNotFoundException {

    public QuestionsLoadingException(String message) {
        super(message);
    }
}
