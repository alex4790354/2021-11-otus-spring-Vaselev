package ru.otus.spring.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import static org.junit.jupiter.api.Assertions.*;

@ComponentScan({"ru.otus.spring.config.ExamConfig"})
@SpringBootTest
class ExamConfigTest {

    @Autowired
    private ExamConfig examConfig;

    @Test
    void getCorrectAnswersToPass() {
        assertEquals(examConfig.getCorrectAnswersToPass(), 4);
    }
}