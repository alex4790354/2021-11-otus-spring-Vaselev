package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.domain.Question;
import ru.otus.spring.service.interfaces.QuestionsService;
import ru.otus.spring.util.QuestionsLoadingException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


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

    /*@DisplayName("Should get 4 records for exam with Russian locale")
    @Test
    void shouldGet4RecordsForRussianLocale() throws QuestionsLoadingException {
        List<Question> questionList = questionsDao.takeExamQuestionsList();
        assertEquals(questionList.size(), QUASTIONS_AMOUNT_EXAM_RU);
    }*/

    /*@DisplayName("Should get 5 records for exam with English locale")
    @Test
    void shouldGet4RecordsForEnglishLocale() throws QuestionsLoadingException {
        //Util.setLocale(Locale.ENGLISH);
        List<Question> questionList = questionsDao.takeExamQuestionsList();
        assertEquals(questionList.size(), QUASTIONS_AMOUNT_EXAM_EN);
    }*/
}