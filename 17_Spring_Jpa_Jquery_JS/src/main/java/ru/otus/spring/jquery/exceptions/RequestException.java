package ru.otus.spring.jquery.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import static org.springframework.http.HttpStatus.BAD_REQUEST;


@ResponseStatus(BAD_REQUEST)
public class RequestException extends RuntimeException {
    public RequestException(String s) {
        super(s);
    }
}