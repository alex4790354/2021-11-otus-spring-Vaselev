package ru.otus.spring.jpa.customExceptions;


public class DaoException extends Exception {

    public DaoException(String reason) {
        super(reason);
    }

}
