package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.service.interfaces.IOService;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class ClosedConsoleIOService implements IOService {

    private final PrintStream out;
    private final Scanner sc;

    public ClosedConsoleIOService() {
        this.out = System.out;
        this.sc = new Scanner(System.in);
    }

    @Override
    public void out(String message) {
        out.println(message);
    }

    @Override
    public String readString() {
        return sc.nextLine();
    }
}
