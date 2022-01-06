package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.shell.Shell;
import ru.otus.spring.config.Config;
import ru.otus.spring.service.interfaces.LocaleProvider;
import ru.otus.spring.service.interfaces.Localization;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Test class LocalizationImpl")
@SpringBootTest
class LocalizationTest {

    @MockBean
    private Shell shell;

    @Autowired
    private Config config;

    @Autowired
    private LocaleProvider localeProvider;

    @Autowired
    private Localization localization;

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
        assertEquals(EXAM_ASK_NAME_RU, localization.getPropertiesValue("exam.ask-name"));
        assertEquals(EXAM_WELCOME_RU , localization.getPropertiesValue("exam.welcome" ));
        assertEquals(EXAM_PASS_RU    , localization.getPropertiesValue("exam.pass"    ));
        assertEquals(EXAM_FAIL_RU    , localization.getPropertiesValue("exam.fail"    ));
    }

    @DisplayName("getExamPropertiesValue should get correct i18n values for EN_US locale:")
    @Test
    void getExamPropertiesValueForEngLocale() {
        localeProvider.setLocale("en", "US");
        assertEquals(EXAM_ASK_NAME_EN, localization.getPropertiesValue("exam.ask-name"));
        assertEquals(EXAM_WELCOME_EN , localization.getPropertiesValue("exam.welcome" ));
        assertEquals(EXAM_PASS_EN    , localization.getPropertiesValue("exam.pass"    ));
        assertEquals(EXAM_FAIL_EN    , localization.getPropertiesValue("exam.fail"    ));
    }

}