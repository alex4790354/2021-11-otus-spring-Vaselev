package ru.otus.spring.Util;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@ConfigurationProperties(prefix = "exam")
@Component
public class ExamConfig {

    private Integer correctAnswersToPass;

    public Integer getCorrectAnswersToPass() {
        return correctAnswersToPass;
    }

    public void setCorrectAnswersToPass(String correctAnswersToPass) {
        this.correctAnswersToPass = Integer.parseInt(correctAnswersToPass);
    }

}
