package ru.otus.spring.utils;

import ru.otus.spring.domain.Question;

public class Convert {

    public static Question getQuestionFromCsvLine(String csvLine) {
        Question question = null;

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

}
