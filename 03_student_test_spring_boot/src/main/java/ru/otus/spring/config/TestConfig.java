package ru.otus.spring.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "test")
@Component
public class TestConfig {

    private static String locale = "ru";

    private String fileName;
    private String correctAnswersToPass;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getCorrectAnswersToPass() {
        return correctAnswersToPass;
    }

    public void setCorrectAnswersToPass(String correctAnswersToPass) {
        this.correctAnswersToPass = correctAnswersToPass;
    }

}
