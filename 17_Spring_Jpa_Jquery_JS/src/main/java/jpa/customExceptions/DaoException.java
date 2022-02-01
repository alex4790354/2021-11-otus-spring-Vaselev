package jpa.customExceptions;


public class DaoException extends RuntimeException {

    public DaoException(String reason) {
        super(reason);
    }

}
