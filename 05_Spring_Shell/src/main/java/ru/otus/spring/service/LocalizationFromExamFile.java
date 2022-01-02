package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.ExamConfig;
import ru.otus.spring.service.interfaces.Localization;

import java.util.Locale;

@Service
public class LocalizationFromExamFile implements Localization {

    private final MessageSource msg;
    private String language;
    private String country;


    @Autowired
    public LocalizationFromExamFile(ExamConfig config, MessageSource msg) {
        this.msg = msg;
        this.language = config.getLocaleLanguage();
        this.country = config.getLocaleCountry();
        System.out.println("Language: " + this.language);
        System.out.println("Country: " + this.country);
    }

    @Override
    public String getExamPropertiesValue(String key, Object ...args) {
        return msg.getMessage(key, args, Locale.forLanguageTag(this.country + "-" + this.language));
    }


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
