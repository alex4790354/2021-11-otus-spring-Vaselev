package ru.otus.spring.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.service.interfaces.QuestionsService;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Test class ExamConfigTest")
@ComponentScan({"ru.otus.spring.config.ExamConfig"})
@SpringBootTest
class ExamConfigTest {

    // Why do I have to Mock  QuestionsService ?? 
    @MockBean
    private QuestionsService questionsService;

    @Autowired
    private ExamConfig examConfig;

    @Test
    void DefaultLocaleShouldBeRuRu() {
        assertEquals(examConfig.getLocale(), new Locale("ru", "RU"));
    }

    // This test fail. Why does it trying to Autowere QuestionServiceImpl?
    @Test
    void getCorrectAnswersToPass() {
        assertEquals(examConfig.getCorrectAnswersToPass(), 4);
    }

}