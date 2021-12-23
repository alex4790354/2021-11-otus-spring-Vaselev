package ru.otus.spring.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Test class UtilTest")
@ComponentScan({"ru.otus.spring.config.util.Util"})
@SpringBootTest
class UtilTest {

    private static final String EXAM_FILE_NAME_EN = "csv/exam-quastions-en.csv";
    private static final String EXAM_WELLCOME_EN = "Welcome to the chemistry test";
    private static final String EXAM_PASS_EN = "Congratulations, you passed the test.";
    private static final String EXAM_FAIL_EN = "You fail the test today, but you can retake it later.";

    private static final String EXAM_FILE_NAME_RU = "csv/exam-quastions-ru.csv";
    private static final String EXAM_WELLCOME_RU = "Russian: Welcome to the chemistry test";
    private static final String EXAM_PASS_RU = "Russian: Congratulations, you passed the test.";
    private static final String EXAM_FAIL_RU = "Russian: You fail the test today, but you can retake it later.";


    @Autowired
    private Util util;

    @DisplayName("getExamPropertiesValue should get correct i18n values for EN_US locale:")
    @Test
    void getExamPropertiesValueForEnLocale() {
        this.util.setLocale(Locale.ENGLISH);
        assertEquals(this.util.getExamPropertiesValue(null, "exam.file-name"), EXAM_FILE_NAME_EN);
        assertEquals(this.util.getExamPropertiesValue(null, "exam.wellcome"), EXAM_WELLCOME_EN);
        assertEquals(this.util.getExamPropertiesValue(null, "exam.pass"), EXAM_PASS_EN);
        assertEquals(this.util.getExamPropertiesValue(null, "exam.fail"), EXAM_FAIL_EN);
    }

    @DisplayName("getExamPropertiesValue should get correct i18n values for ru_RU locale:")
    @Test
    void getExamPropertiesValueForRuLocale() {
        this.util.setLocale(new Locale("ru", "RU"));
        assertEquals(this.util.getExamPropertiesValue(null, "exam.file-name"), EXAM_FILE_NAME_RU);
        assertEquals(this.util.getExamPropertiesValue(null, "exam.wellcome"), EXAM_WELLCOME_RU);
        assertEquals(this.util.getExamPropertiesValue(null, "exam.pass"), EXAM_PASS_RU);
        assertEquals(this.util.getExamPropertiesValue(null, "exam.fail"), EXAM_FAIL_RU);
    }
}