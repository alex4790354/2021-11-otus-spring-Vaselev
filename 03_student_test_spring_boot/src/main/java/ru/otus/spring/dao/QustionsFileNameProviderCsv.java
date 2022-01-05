package ru.otus.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.spring.config.Config;
import ru.otus.spring.dao.interfaces.QustionsFileNameProvider;


@Component
public class QustionsFileNameProviderCsv implements QustionsFileNameProvider {

    private final Config config;

    @Autowired
    public QustionsFileNameProviderCsv(Config config) {
        this.config = config;
    }

    @Override
    public String getFileName() {
        return String.format("%s-%s.csv", config.getExamFileNameCsv(), config.getLocaleLanguage());
    }
}
