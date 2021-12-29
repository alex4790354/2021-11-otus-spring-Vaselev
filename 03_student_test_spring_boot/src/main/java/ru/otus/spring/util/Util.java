package ru.otus.spring.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.spring.config.ExamConfig;

import java.util.Locale;

@Component
public class Util {

    ExamConfig config;
    private Locale locale;
    MessageSource msg;                      //alterantive:  private ReloadableResourceBundleMessageSource msg;
                                            //              this.msg = new ReloadableResourceBundleMessageSource();
                                            //              this.msg.setBasename("classpath:i18n/message");

    @Autowired
    public Util(MessageSource messageSource, ExamConfig examConfig) {
        this.config = examConfig;
        this.msg = messageSource;
        this.locale = Locale.forLanguageTag(config.getLocaleLanguage() + "-" + config.getLocaleCountry());
    }

    public String getCsvExamFileName() {
        return getExamPropertiesValue(null, "exam.file-name");
    }

    public String getExamPropertiesValue(Object[] obj, String key) {
        return msg.getMessage(key, obj, this.locale);                               //new Object[]{"any text"},
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
