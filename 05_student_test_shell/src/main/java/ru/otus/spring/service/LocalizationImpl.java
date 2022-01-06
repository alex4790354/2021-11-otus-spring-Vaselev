package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.service.interfaces.LocaleProvider;
import ru.otus.spring.service.interfaces.Localization;

import java.util.Locale;

@Service
public class LocalizationImpl implements Localization {

    private final MessageSource msg;
    private final LocaleProvider localeProvider;

    @Autowired
    public LocalizationImpl(MessageSource msg, LocaleProvider localeProvider) {
        this.msg = msg;
        this.localeProvider = localeProvider;
    }

    @Override
    public String getPropertiesValue(String key, Object ...args) {
        return msg.getMessage(key, args, this.localeProvider.getLocale());
    }

}
