package ru.otus.spring.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.config.ExamConfig;
import ru.otus.spring.service.LocalizationFromExamFile;
import ru.otus.spring.service.interfaces.Localization;
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
    private LocalizationFromExamFile localization;

    private static final String EXAM_ASK_NAME_EN = "what is your name?";
    private static final String EXAM_WELCOME_EN = "Welcome to the chemistry test.";
    private static final String EXAM_PASS_EN = "Congratulations, you passed the test.";
    private static final String EXAM_FAIL_EN = "You fail the test today, but you can retake it later.";

    private static final String EXAM_ASK_NAME_RU = "Русский: what is your name?";
    private static final String EXAM_WELCOME_RU = "Русский: Welcome to the chemistry test";
    private static final String EXAM_PASS_RU = "Русский: Congratulations, you passed the test.";
    private static final String EXAM_FAIL_RU = "Русский: You fail the test today, but you can retake it later.";

    @DisplayName("getExamPropertiesValue should get correct i18n values for ru_RU locale:")
    @Test
    void getExamPropertiesValueForRusLocale() {
        //util.setLocale(new Locale("ru", "RU"));
        examConfig.setLocaleLanguage("ru");
        examConfig.setLocaleCountry("RU");
        assertEquals(localization.getExamPropertiesValue("exam.ask-name", null), EXAM_ASK_NAME_RU);
        assertEquals(localization.getExamPropertiesValue("exam.welcome",  null), EXAM_WELCOME_RU);
        assertEquals(localization.getExamPropertiesValue("exam.pass",     null), EXAM_PASS_RU);
        assertEquals(localization.getExamPropertiesValue("exam.fail",     null), EXAM_FAIL_RU);
    }

    @DisplayName("getExamPropertiesValue should get correct i18n values for EN_US locale:")
    @Test
    void getExamPropertiesValueForEngLocale() {
        //util.setLocale(Locale.ENGLISH);
        /*examConfig.setLocaleLanguage("en");
        examConfig.setLocaleCountry("US");*/
        localization.setLanguage("ru");
        localization.setCountry("US");
        assertEquals(localization.getExamPropertiesValue("exam.ask-name", null), EXAM_ASK_NAME_EN);
        assertEquals(localization.getExamPropertiesValue("exam.welcome" , null), EXAM_WELCOME_EN);
        assertEquals(localization.getExamPropertiesValue("exam.pass",     null), EXAM_PASS_EN);
        assertEquals(localization.getExamPropertiesValue("exam.fail",     null), EXAM_FAIL_EN);
    }

}