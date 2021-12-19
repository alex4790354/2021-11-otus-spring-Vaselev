package ru.otus.spring.domain;

public class Question {
    int id;
    String questionText;
    String answer;

    public Question(String id, String questionText, String answer) {
        this.id = Integer.parseInt(id);
        this.questionText = questionText;
        this.answer = answer;
    }

    // Why do I need it?
    public Question() {
        this.id = 0;
        this.questionText = "questionText ???";
        this.answer = "answer ???";
    }

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
