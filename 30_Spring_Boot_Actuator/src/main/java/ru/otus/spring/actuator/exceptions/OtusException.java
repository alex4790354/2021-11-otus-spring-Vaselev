package ru.otus.spring.actuator.exceptions;


public class OtusException extends RuntimeException {

    public OtusException(String reason) {
        super(reason);
    }

}
