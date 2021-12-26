package ru.otus.spring.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import ru.otus.spring.util.Util;

import java.util.Locale;


@ConfigurationProperties(prefix = "exam")
@Component
public class ExamConfig {

    private final ReloadableResourceBundleMessageSource examSource;
    private Integer correctAnswersToPass;
    private Locale locale;

    public ExamConfig() {
        this.examSource = new ReloadableResourceBundleMessageSource();
        this.examSource.setBasename("classpath:i18n/exam");
        this.locale = new Locale("ru", "RU");
    }

    public String getExamPropertiesValue(Object[] obj, String key) {
        return examSource.getMessage(key, obj, this.locale);                    //new Object[]{"any text"},
    }

    public Integer getCorrectAnswersToPass() {
        return correctAnswersToPass;
    }

    /*public void setCorrectAnswersToPass(String correctAnswersToPass) {
        this.correctAnswersToPass = Integer.parseInt(correctAnswersToPass);
    }*/

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
