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
        this.examSource.setBasename("classpath:exam");
    }

    // Static Method for writing output
    public boolean SendMessage(String outputType, String message) {
        if (outputType.equals("Screen")) {
            System.out.println("Util: " + message);
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

    public String getExamPropertiesValue(String key) {
        return examSource.getMessage(key,
                null,                                               //new Object[]{"any text"},
                this.locale);
    }

}
