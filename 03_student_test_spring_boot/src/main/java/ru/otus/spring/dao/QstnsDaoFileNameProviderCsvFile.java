package ru.otus.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.spring.config.ExamConfig;
import ru.otus.spring.dao.interfaces.QuestionsDaoFileNameProvider;


@Component
public class QuestionsDaoFileNameProviderCsvFile implements QuestionsDaoFileNameProvider {

    private final ExamConfig examConfig;

    @Autowired
    public QuestionsDaoFileNameProviderCsvFile(ExamConfig examConfig) {
        this.examConfig = examConfig;
    }

    @Override
    public String getFileName() {
        return String.format("%s-%s.csv", examConfig.getExamFileNameCsv(), examConfig.getLocaleLanguage());
    }
}
