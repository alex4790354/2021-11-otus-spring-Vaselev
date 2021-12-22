package ru.otus.spring.Util;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@ConfigurationProperties(prefix = "exam")
@Component
public class ExamConfig {

    private String correctAnswersToPass;

    public String getCorrectAnswersToPass() {
        return correctAnswersToPass;
    }

    public void setCorrectAnswersToPass(String correctAnswersToPass) {
        this.correctAnswersToPass = correctAnswersToPass;
    }

}
