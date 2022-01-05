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
        assertEquals(25, question.getId());
        assertEquals("Who are you?", question.getQuestionText());
        assertEquals("3", question.getAnswer());
    }

}