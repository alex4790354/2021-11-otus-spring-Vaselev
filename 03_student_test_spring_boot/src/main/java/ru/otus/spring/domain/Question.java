package ru.otus.spring.domain;

public class Question {
    long id;
    String questionText;
    String answer;

    public String getAnswer() {
        return answer;
    }

    public long getId() {
        return id;
    }

    public String getQuestionText() {
        return questionText;
    }

}
