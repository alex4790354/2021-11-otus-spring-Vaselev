package ru.otus.spring.jquery.exceptions;


public class DaoException extends RuntimeException {

    public DaoException(String reason) {
        super(reason);
    }

}
