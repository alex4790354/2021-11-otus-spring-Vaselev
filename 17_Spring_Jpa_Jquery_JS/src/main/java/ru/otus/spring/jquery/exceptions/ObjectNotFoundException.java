package ru.otus.spring.jquery.exceptions;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.ResponseStatus;
import static org.springframework.http.HttpStatus.NOT_FOUND;


@ResponseStatus(NOT_FOUND)
public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String s) {
        super(s);
    }

    public ObjectNotFoundException(String s, EmptyResultDataAccessException e) {
        super(s);
    }
}