package ru.otus.spring.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import java.util.Locale;


@ConfigurationProperties(prefix = "exam")
@Component
public class Config {

    private Integer correctAnswersToPass;
    private String localeLanguage;
    private String localeCountry;
    private String examFileNameCsv;

    public Integer getCorrectAnswersToPass() {
        return correctAnswersToPass;
    }

    public void setCorrectAnswersToPass(Integer correctAnswersToPass) {
        this.correctAnswersToPass = correctAnswersToPass;
    }

    public String getLocaleLanguage() {
        return localeLanguage;
    }

    public void setLocaleLanguage(String localeLanguage) {
        this.localeLanguage = localeLanguage;
    }

    public String getLocaleCountry() {
        return localeCountry;
    }

    public void setLocaleCountry(String localeCountry) {
        this.localeCountry = localeCountry;
    }

    public void setExamFileNameCsv(String examFileNameCsv) {
        this.examFileNameCsv = examFileNameCsv;
    }

    public String getExamFileNameCsv() {
        return examFileNameCsv;
    }

}
