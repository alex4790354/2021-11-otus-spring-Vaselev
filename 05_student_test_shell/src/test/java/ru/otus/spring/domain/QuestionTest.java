package ru.otus.spring.domain;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Test class Question")
class QuestionTest {

    @DisplayName("Question Should Have Correct Constructor")
    @Test
    void shouldHaveCorrectConstructor() {
        Question question = new Question(25, "Who are you?", "3");
        assertEquals(question.getId(), 25);
        assertEquals(question.getQuestionText(), "Who are you?");
        assertEquals(question.getAnswer(), "3");
    }

}