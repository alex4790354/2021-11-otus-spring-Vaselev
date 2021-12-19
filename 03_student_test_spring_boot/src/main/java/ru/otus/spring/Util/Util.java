package ru.otus.spring.Util;


import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Util {

    // Static Method for writing output
    public static boolean SendMessage(String outputType, String message) {
        if (outputType.equals("Screen")) {
            System.out.println("Util: " + message);
            return true;
        } else {
            return false;
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

}
