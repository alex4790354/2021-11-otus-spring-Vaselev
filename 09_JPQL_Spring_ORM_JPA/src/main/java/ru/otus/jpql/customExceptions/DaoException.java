package ru.otus.jpql.customExceptions;


public class DaoException extends Exception {

    public DaoException(String reason) {
        super(reason);
    }

}
