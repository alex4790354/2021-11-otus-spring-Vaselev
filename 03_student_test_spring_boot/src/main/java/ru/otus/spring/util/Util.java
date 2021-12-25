package ru.otus.spring.util;


import org.springframework.context.support.ReloadableResourceBundleMessageSource;
//import org.springframework.stereotype.Component;
import java.util.Locale;
import java.util.Scanner;



public class Util {

    private static Locale locale = new Locale("ru", "RU");
    //private static ReloadableResourceBundleMessageSource examSource = new ReloadableResourceBundleMessageSource();

    /*public static void setExamSource(String path) {
        //this.locale = Locale.ENGLISH;
        //locale = new Locale("ru", "RU");
        //examSource = new ReloadableResourceBundleMessageSource();
        examSource.setBasename(path);
    }*/

    /*static {
        //this.locale = Locale.ENGLISH;
        //locale = new Locale("ru", "RU");
        //examSource = new ReloadableResourceBundleMessageSource();
        examSource.setBasename("classpath:i18n/exam");
    }*/

    public static void SendMessage(String outputType, String message) {
        if (outputType.equals("Screen")) {
            System.out.println(message);
        }
    }

    public static String ReadMessage(String sourceType) {
        if (sourceType.equals("Screen")) {
            Scanner console = new Scanner(System.in);
            String input = console.nextLine();
            return input;
        } else {
            return "--Wrong source--";
        }
    }

    /*public static String getExamPropertiesValue(Object[] obj, String key) {
        //examSource.setBasename("classpath:i18n/exam");
        return examSource.getMessage(key, obj, locale);                    //new Object[]{"any text"},
    }*/

    public static void setLocale(Locale newLocale) {
        locale = newLocale;
    }

    public static Locale getLocale() {
        return locale;
    }
}
