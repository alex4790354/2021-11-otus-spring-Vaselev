package ru.otus.spring.utils;

import ru.otus.spring.domain.Question;


public class Util {

    public static Question convertCsvLineToQuestion(String csvLine) {
        Question question = null;

        // TODO: convert stipping to library.
        String[] stringArray = csvLine.split(",");
        if (stringArray.length > 2) {
            // TODO: catch not Integer in stringArray[0]
            int id = Integer.parseInt(stringArray[0]);
            String questionText = stringArray[1];
            String answer = stringArray[2];
            question = new Question(id, questionText, answer);
        }
        return question;
    }

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
