package ru.otus.spring.service;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.service.interfaces.IOService;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тест IOServiceOpenConsole")
class IOServOpenConsoleTest {
    private static final String TEXT_TO_PRINT1 = "Hello world 108";
    private static final String TEXT_TO_PRINT2 = "How are you?";

    private PrintStream backup;
    private ByteArrayOutputStream bos;
    ;
    private IOService ioService;

    @BeforeEach
    void setUp() {
        System.out.println(Thread.currentThread().getName());
        backup = System.out;
        bos = new ByteArrayOutputStream();
        ioService = new IOServOpenConsole(new PrintStream(bos), System.in);
    }

    @AfterEach
    void tearDown() {
        System.setOut(backup);
    }

    @DisplayName(" Have to read \"" + TEXT_TO_PRINT1 + "\"")
    @SneakyThrows
    @Test
    void shouldPrintOnlyFirstLine() {
        ioService.out(TEXT_TO_PRINT1);
        Thread.sleep(1000);
        assertEquals(bos.toString(), TEXT_TO_PRINT1 + System.lineSeparator());
    }

    @DisplayName(" Have to read \"" + TEXT_TO_PRINT2 + "\"")
    @SneakyThrows
    @Test
    void shouldPrintOnlySecondLine() {
        ioService.out(TEXT_TO_PRINT2);
        assertEquals(bos.toString(), TEXT_TO_PRINT2 + System.lineSeparator());
    }
}