package ru.otus.spring.jpa.customExceptions;


public class DaoException extends RuntimeException {

    public DaoException(String reason) {
        super(reason);
    }

}
