package ru.otus.spring.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.spring.config.ExamConfig;

import java.util.Locale;

@Component
public class Util {

    //private Locale locale;
    private final ExamConfig config;
    private final MessageSource msg;                        // alterantive:     private ReloadableResourceBundleMessageSource msg;
                                                            //                  this.msg = new ReloadableResourceBundleMessageSource();
                                                            //                  this.msg.setBasename("classpath:i18n/message");

    @Autowired
    public Util(ExamConfig config, MessageSource msg) {
        this.config = config;
        this.msg = msg;
    }

    public String getExamPropertiesValue(Object[] obj, String key) {
        return msg.getMessage(key, obj, Locale.forLanguageTag(config.getLocaleLanguage() + "-" + config.getLocaleCountry()));                               //new Object[]{"any text"},
    }

}
