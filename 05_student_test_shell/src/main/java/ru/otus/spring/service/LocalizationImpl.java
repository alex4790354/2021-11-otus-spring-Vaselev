package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.service.interfaces.Localization;

import java.util.Locale;

@Service
public class LocalizationImpl implements Localization {

    private final MessageSource msg;

    @Autowired
    public LocalizationImpl(MessageSource msg) {
        this.msg = msg;
    }

    @Override
    public String getPropertiesValue(String key, String language, String country, Object ...args) {
        System.out.println("key: " + key + ", language: " + language + ", country: " + country);
        return msg.getMessage(key, args, Locale.forLanguageTag(language + "-" + country));
    }

}
