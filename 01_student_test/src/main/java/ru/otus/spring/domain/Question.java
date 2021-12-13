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

    public int getId() {
        return id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getAnswer() {
        return answer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
