package ru.otus.spring.service.interfaces;

import java.util.Locale;

public interface LocaleProvider {
    void setLocale(String language, String country);
    Locale getLocale();
}
