package ru.otus.spring.integration.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.spring.integration.util.Util;

@AllArgsConstructor
@Data
public class Report {

    private Long studentId;
    private int testResult;

    public Report(Long studentId) {
        this.studentId = studentId;
        this.testResult = Util.faker().random().nextInt(1, 100);
    }
}
