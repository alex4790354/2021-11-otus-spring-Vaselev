package ru.otus.spring.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.shell.Shell;
import ru.otus.spring.service.interfaces.QuestionsService;
import ru.otus.spring.shell.ApplicationEventsCommands;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Test class ExamConfigTest")
@ComponentScan({"ru.otus.spring.config"})
@SpringBootTest
class ConfigTest {

    // Why do I have to Mock  Shell ??
    @MockBean
    private Shell shell;

    @Autowired
    private Config config;


    // This test fail. Why does it trying to Autowere QuestionServiceImpl?
    @Test
    void getCorrectAnswersToPass() {
        assertEquals(config.getCorrectAnswersToPass(), 3);
    }

}