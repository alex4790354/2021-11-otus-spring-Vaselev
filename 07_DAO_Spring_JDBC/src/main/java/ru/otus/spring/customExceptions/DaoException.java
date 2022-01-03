package ru.otus.spring.customExceptions;


public class DaoException extends Exception {

    public DaoException(String reason) {
        super(reason);
    }

}
