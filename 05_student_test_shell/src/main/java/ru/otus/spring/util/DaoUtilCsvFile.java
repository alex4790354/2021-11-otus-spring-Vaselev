package ru.otus.spring.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.spring.config.ExamConfig;
import ru.otus.spring.util.interfaces.DaoUtil;


@Component
public class DaoUtilCsvFile implements DaoUtil {

    ExamConfig examConfig;

    @Autowired
    public DaoUtilCsvFile(ExamConfig examConfig) {
        this.examConfig = examConfig;
    }

    @Override
    public String getFileName() {
        return String.format("%s-%s.csv", examConfig.getExamFileNameCsv(), examConfig.getLocaleLanguage());
    }
}
