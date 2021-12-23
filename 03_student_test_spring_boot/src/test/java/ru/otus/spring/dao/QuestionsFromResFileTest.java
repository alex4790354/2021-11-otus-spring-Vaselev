package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.domain.Question;
import ru.otus.spring.util.QuestionsLoadingException;
import ru.otus.spring.util.Util;
import java.util.List;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Test class QuestionsFromResFileTest")
@ComponentScan({"ru.otus.spring.config.util.Util",
                "ru.otus.spring.config.QuestionsFromResFile"})
@SpringBootTest
class QuestionsFromResFileTest {

    private static final int QUASTIONS_AMOUNT_EXAM_RU = 4;
    private static final int QUASTIONS_AMOUNT_EXAM_EN = 5;

    @Autowired
    private QuestionsDao questionsDao;

    @Autowired
    private Util util;

    @DisplayName("Should get 4 records for exam with Russian locale")
    @Test
    void shouldGet4RecordsForRussianLocale() throws QuestionsLoadingException {
        util.setLocale(new Locale("ru", "RU"));
        List<Question> questionList = questionsDao.takeExam();
        assertEquals(questionList.size(), QUASTIONS_AMOUNT_EXAM_RU);
    }

    @DisplayName("Should get 5 records for exam with English locale")
    @Test
    void shouldGet4RecordsForEnglishLocale() throws QuestionsLoadingException {
        util.setLocale(Locale.ENGLISH);
        List<Question> questionList = questionsDao.takeExam();
        assertEquals(questionList.size(), QUASTIONS_AMOUNT_EXAM_EN);
    }
}