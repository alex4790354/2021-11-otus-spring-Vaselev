package ru.otus.spring.util;

import java.io.FileNotFoundException;

public class HeaderLoadingException extends ArrayIndexOutOfBoundsException {

    public HeaderLoadingException(String message) {
        super(message);
    }
}
