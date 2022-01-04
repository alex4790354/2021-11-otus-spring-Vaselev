package ru.otus.spring.domain;

public class Question {

    private long id;
    private String questionText;
    private String answer;

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

    public void setQuestion(String name, String value) {
        if (name.equals("id")) {
            this.id = Integer.parseInt(value);
        } else if (name.equals("questionText")) {
            this.questionText = value;
        } else if (name.equals("answer")) {
            this.answer = value;
        }
    }

}
