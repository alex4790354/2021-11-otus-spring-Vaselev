package ru.otus.spring.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import ru.otus.spring.util.Util;


@ConfigurationProperties(prefix = "exam")
@Component
public class ExamConfig {

    private Integer correctAnswersToPass;
    private ReloadableResourceBundleMessageSource examSource;

    public ExamConfig() {
        examSource = new ReloadableResourceBundleMessageSource();
        examSource.setBasename("classpath:i18n/exam");
    }

    public String getExamPropertiesValue(Object[] obj, String key) {
        return examSource.getMessage(key, obj, Util.getLocale());                    //new Object[]{"any text"},
    }

    public Integer getCorrectAnswersToPass() {
        return correctAnswersToPass;
    }

    public void setCorrectAnswersToPass(String correctAnswersToPass) {
        this.correctAnswersToPass = Integer.parseInt(correctAnswersToPass);
    }

}
