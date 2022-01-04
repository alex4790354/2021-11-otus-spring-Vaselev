package ru.otus.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.spring.config.ExamConfig;
import ru.otus.spring.dao.interfaces.QstnsDaoFileNameProvider;


@Component
public class QstnsDaoFileNameProviderCsvFile implements QstnsDaoFileNameProvider {

    private final ExamConfig examConfig;

    @Autowired
    public QstnsDaoFileNameProviderCsvFile(ExamConfig examConfig) {
        this.examConfig = examConfig;
    }

    @Override
    public String getFileName() {
        return String.format("%s-%s.csv", examConfig.getExamFileNameCsv(), examConfig.getLocaleLanguage());
    }
}
