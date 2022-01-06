package ru.otus.spring.util;


public class ExamException extends RuntimeException {

    public ExamException(String message, Exception exc) {
        super(message + System.lineSeparator() + exc.getMessage());
    }

}
