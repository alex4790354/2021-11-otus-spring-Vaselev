package ru.otus.spring.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.config.ExamConfig;
import ru.otus.spring.service.interfaces.QuestionsService;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Test class UtilTest")
@ComponentScan({"ru.otus.spring.config.ExamConfig"})
@SpringBootTest
class UtilTest {

    // Why do I have to Mock  QuestionsService ??
    @MockBean
    private QuestionsService questionsService;

    @Autowired
    private ExamConfig examConfig;

    @Autowired
    private Util util;

    private static final String EXAM_ASK_NAME_EN = "what is your name?";
    private static final String EXAM_WELCOME_EN = "Welcome to the chemistry test";
    private static final String EXAM_PASS_EN = "Congratulations, you passed the test.";
    private static final String EXAM_FAIL_EN = "You fail the test today, but you can retake it later.";

    private static final String EXAM_ASK_NAME_RU = "Russian: what is your name?";
    private static final String EXAM_WELCOME_RU = "Russian: Welcome to the chemistry test";
    private static final String EXAM_PASS_RU = "Russian: Congratulations, you passed the test.";
    private static final String EXAM_FAIL_RU = "Russian: You fail the test today, but you can retake it later.";

    @DisplayName("getExamPropertiesValue should get correct i18n values for ru_RU locale:")
    @Test
    void getExamPropertiesValueForRusLocale() {
        //util.setLocale(new Locale("ru", "RU"));
        examConfig.setLocaleLanguage("ru");
        examConfig.setLocaleCountry("RU");
        assertEquals(util.getExamPropertiesValue(null, "exam.ask-name"), EXAM_ASK_NAME_RU);
        assertEquals(util.getExamPropertiesValue(null, "exam.welcome"), EXAM_WELCOME_RU);
        assertEquals(util.getExamPropertiesValue(null, "exam.pass"), EXAM_PASS_RU);
        assertEquals(util.getExamPropertiesValue(null, "exam.fail"), EXAM_FAIL_RU);
    }

    @DisplayName("getExamPropertiesValue should get correct i18n values for EN_US locale:")
    @Test
    void getExamPropertiesValueForEngLocale() {
        //util.setLocale(Locale.ENGLISH);
        examConfig.setLocaleLanguage("en");
        examConfig.setLocaleCountry("US");
        assertEquals(util.getExamPropertiesValue(null, "exam.ask-name"), EXAM_ASK_NAME_EN);
        assertEquals(util.getExamPropertiesValue(null, "exam.welcome"), EXAM_WELCOME_EN);
        assertEquals(util.getExamPropertiesValue(null, "exam.pass"), EXAM_PASS_EN);
        assertEquals(util.getExamPropertiesValue(null, "exam.fail"), EXAM_FAIL_EN);
    }

}