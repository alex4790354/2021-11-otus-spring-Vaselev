package ru.otus.spring.utils;


public class Util {

    // Static Method for writing output
    public static boolean SendMessage(String outputType, String message) {
        if (outputType.equals("Screen")) {
            System.out.println(message);
            return true;
        } else {
            return false;
        }
    }

}
