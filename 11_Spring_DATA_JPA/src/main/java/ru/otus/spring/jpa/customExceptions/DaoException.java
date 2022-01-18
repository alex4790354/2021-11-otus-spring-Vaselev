package ru.otus.spring.jpa.customExceptions;


public class DaoException extends Exception {

    public DaoException(String reason, Exception e) throws Exception {
        super(reason);
        throw e;
    }

}
