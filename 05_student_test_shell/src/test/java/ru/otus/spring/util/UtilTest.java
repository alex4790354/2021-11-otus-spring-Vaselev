package ru.otus.spring.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.shell.Shell;
import ru.otus.spring.config.Config;
import ru.otus.spring.service.LocalizationImpl;
import ru.otus.spring.service.interfaces.QuestionsService;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Test class UtilTest")
@ComponentScan({"ru.otus.spring.config.Config"})
@SpringBootTest
class UtilTest {

    // Why do I have to Mock  Shell ??
    @MockBean
    private Shell shell;

    @Autowired
    private Config config;

    @Autowired
    private LocalizationImpl localization;

    private static final String EXAM_ASK_NAME_EN = "what is your name?";
    private static final String EXAM_WELCOME_EN = "Welcome to the chemistry test";
    private static final String EXAM_PASS_EN = "Congratulations, you passed the test.";
    private static final String EXAM_FAIL_EN = "You fail the test today, but you can retake it later.";

    private static final String EXAM_ASK_NAME_RU = "Русский: what is your name?";
    private static final String EXAM_WELCOME_RU = "Русский: Welcome to the chemistry test";
    private static final String EXAM_PASS_RU = "Русский: Congratulations, you passed the test.";
    private static final String EXAM_FAIL_RU = "Русский: You fail the test today, but you can retake it later.";

    @DisplayName("getExamPropertiesValue should get correct i18n values for ru_RU locale:")
    @Test
    void getExamPropertiesValueForRusLocale() {
        config.setLocaleLanguage("ru");
        config.setLocaleCountry("RU");
        assertEquals(localization.getPropertiesValue("exam.ask-name", config.getLocaleLanguage(), config.getLocaleCountry()), EXAM_ASK_NAME_RU);
        assertEquals(localization.getPropertiesValue("exam.welcome" , config.getLocaleLanguage(), config.getLocaleCountry()), EXAM_WELCOME_RU);
        assertEquals(localization.getPropertiesValue("exam.pass"    , config.getLocaleLanguage(), config.getLocaleCountry()), EXAM_PASS_RU);
        assertEquals(localization.getPropertiesValue("exam.fail"    , config.getLocaleLanguage(), config.getLocaleCountry()), EXAM_FAIL_RU);
    }

    @DisplayName("getExamPropertiesValue should get correct i18n values for EN_US locale:")
    @Test
    void getExamPropertiesValueForEngLocale() {
        config.setLocaleLanguage("en");
        config.setLocaleCountry("US");
        assertEquals(localization.getPropertiesValue("exam.ask-name", config.getLocaleLanguage(), config.getLocaleCountry()), EXAM_ASK_NAME_EN);
        assertEquals(localization.getPropertiesValue("exam.welcome" , config.getLocaleLanguage(), config.getLocaleCountry()), EXAM_WELCOME_EN);
        assertEquals(localization.getPropertiesValue("exam.pass"    , config.getLocaleLanguage(), config.getLocaleCountry()), EXAM_PASS_EN);
        assertEquals(localization.getPropertiesValue("exam.fail"    , config.getLocaleLanguage(), config.getLocaleCountry()), EXAM_FAIL_EN);
    }

}