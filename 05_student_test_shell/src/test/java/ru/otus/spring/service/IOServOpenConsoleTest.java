package ru.otus.spring.service;

import lombok.SneakyThrows;
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
    private ByteArrayOutputStream bos;
    private IOService ioService;

    @BeforeEach
    void setUp() {
        bos = new ByteArrayOutputStream();
        ioService = new IOServOpenConsole(new PrintStream(bos), System.in);
    }

    @DisplayName(" Have to read \"" + TEXT_TO_PRINT1 + "\"")
    @SneakyThrows
    @Test
    void shouldPrintOnlyFirstLine() {
        ioService.out(TEXT_TO_PRINT1);
        assertEquals(TEXT_TO_PRINT1 + System.lineSeparator(), bos.toString());
    }

}