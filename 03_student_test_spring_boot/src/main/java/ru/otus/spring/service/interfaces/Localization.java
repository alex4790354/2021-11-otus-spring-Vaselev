package ru.otus.spring.service.interfaces;

public interface Localization {

    String getPropertiesValue(String key, String language, String country, Object ...args);

}
