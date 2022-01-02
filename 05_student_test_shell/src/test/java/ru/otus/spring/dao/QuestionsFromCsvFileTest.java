package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.service.interfaces.QuestionsService;


@DisplayName("Test class QuestionsFromResFileTest")
@ComponentScan({"ru.otus.spring.config.QuestionsFromResFile"})
@SpringBootTest
class QuestionsFromCsvFileTest {

    // Why do I have to Mock  QuestionsService ??
    @MockBean
    private QuestionsService questionsService;

    private static final int QUASTIONS_AMOUNT_EXAM_RU = 4;
    private static final int QUASTIONS_AMOUNT_EXAM_EN = 5;

    @Autowired
    private QuestionsDao questionsDao;


}