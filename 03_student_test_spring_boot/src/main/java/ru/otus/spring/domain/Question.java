package ru.otus.spring.domain;

public class Question {
    int id;
    String questionText;
    String answer;

    @Override
    public String toString() {
        return String.format("Question id: %s, questionText: %s, answer: %s", this.id, this.questionText, this.answer);
    }

    public String getAnswer() {
        return answer;
    }

    public int getId() {
        return id;
    }

    public String getQuestionText() {
        return questionText;
    }

}
