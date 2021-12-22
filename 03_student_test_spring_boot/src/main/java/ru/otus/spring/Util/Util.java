package ru.otus.spring.Util;


import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import java.util.Locale;
import java.util.Scanner;


@Component
public class Util {

    private Locale locale;
    private ReloadableResourceBundleMessageSource examSource;

    public Util() {
        this.locale = new Locale("ru", "RU");;
        this.examSource = new ReloadableResourceBundleMessageSource();
        this.examSource.setBasename("classpath:i18n/exam");
    }

    public boolean SendMessage(String outputType, String message) {
        if (outputType.equals("Screen")) {
            System.out.println(message);
            return true;
        } else {
            return false;
        }
    }

    public String ReadMessage(String sourceType) {
        if (sourceType.equals("Screen")) {
            Scanner console = new Scanner(System.in);
            String input = console.nextLine();
            return input;
        } else {
            return "--Wrong source--";
        }
    }

    public String getExamPropertiesValue(Object[] obj, String key) {
        return examSource.getMessage(key, obj, this.locale);                    //new Object[]{"any text"},
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
