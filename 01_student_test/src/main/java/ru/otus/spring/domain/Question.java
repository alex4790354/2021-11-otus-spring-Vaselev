package ru.otus.spring.domain;

public class Question {
    private int id;
    private String questionText;
    private String answer;

    public Question(int id, String questionText, String answer) {
        this.id = id;
        this.questionText = questionText;
        this.answer = answer;
    }

    public Question(String[] stringArray) {
        if (stringArray.length > 2) {
            // TODO: catch not Integer in stringArray[0]
            this.id = Integer.parseInt(stringArray[0]);
            this.questionText = stringArray[1];
            this.answer = stringArray[2];
        }
    }

    @Override
    public String toString() {
        return String.format("Question id: %s, questionText: %s, answer: %s", this.id, this.questionText, this.answer);
    }

}
