package ru.otus.spring.domain;

public class Question {
    private int id;
    private String questionText;
    private String answer;

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
