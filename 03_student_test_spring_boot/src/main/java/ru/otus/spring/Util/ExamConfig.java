package ru.otus.spring.Util;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;



/**
 *
 * I don't use this class anymore, but leave just as onother example to reading application.properties file
 *
 **/

@ConfigurationProperties(prefix = "test")
@Component
public class ExamConfig {

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
