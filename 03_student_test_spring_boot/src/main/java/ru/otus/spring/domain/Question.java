package ru.otus.spring.domain;

public class Question {

    private long id;
    private String questionText;
    String answer;

    public Question() {
    }

    public Question(long id, String questionText, String answer) {
        this.id = id;
        this.questionText = questionText;
        this.answer = answer;
    }

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
