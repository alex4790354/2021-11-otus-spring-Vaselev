package ru.otus.spring.util;


import java.util.Scanner;


public class Util {

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

}
