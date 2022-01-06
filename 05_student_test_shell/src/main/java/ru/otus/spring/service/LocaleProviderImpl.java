package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.spring.config.Config;
import ru.otus.spring.service.interfaces.LocaleProvider;

import java.util.Locale;

@Component
public class LocaleProviderImpl implements LocaleProvider {

    private final Config config;
    private Locale locale;

    @Autowired
    public LocaleProviderImpl(Config config) {
        this.config = config;
        this.locale = Locale.forLanguageTag(this.config.getLocaleLanguage() + "-" + this.config.getLocaleCountry());
    }


    @Override
    public void setLocale(String language, String country) {
        this.locale = Locale.forLanguageTag(language + "-" + country);
    }

    @Override
    public Locale getLocale() {
        return this.locale;
    }
}
