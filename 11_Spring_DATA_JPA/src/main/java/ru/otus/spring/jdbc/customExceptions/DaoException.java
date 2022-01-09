package ru.otus.spring.jdbc.customExceptions;


public class DaoException extends Exception {

    public DaoException(String reason) {
        super(reason);
    }

}
