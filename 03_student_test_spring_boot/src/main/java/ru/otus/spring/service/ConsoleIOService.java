package ru.otus.spring.service;

import lombok.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.service.interfaces.IOService;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class ConsoleIOService implements IOService {

    private final PrintStream out;
    private final Scanner sc;

    // This constructor doesn't work for me.
    // Can you please let me know why?
    // TODO: Fix this:
    /*public ConsoleIOService(@Value("#{ T(java.lang.System).in}") InputStream in,
                                @Value("#{ T(java.lang.System).out}") PrintStream out) {
        this.out = out;
        this.sc = new Scanner(in);
    }*/

    // TODO: Delete this constructor after fixing correct one:
    public ConsoleIOService() {
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
