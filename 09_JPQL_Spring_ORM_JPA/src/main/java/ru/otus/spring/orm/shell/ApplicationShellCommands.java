package ru.otus.spring.orm.shell;


import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.orm.repositories.BookRepository;


@ShellComponent
@RequiredArgsConstructor
// We don't need it yet, but can use it later
public class ApplicationShellCommands {

    private final BookRepository bookRepository;
    private String userName;

    private Availability isAuthorized() {
        return this.userName == null? Availability.unavailable("Сначала авторизуйтесь"): Availability.available();
    }

    @ShellMethod(value = "Авторизация для пользования библиотекой.", key = {"l", "login"})
    public String login(@ShellOption(defaultValue = "Incognito") String userName) {
        this.userName = userName;
        return String.format("%s, добро пожаловать в библиотеку)", this.userName);
    }

    @ShellMethod(value = "Get all", key = {"alb", "allBooks"})
    @ShellMethodAvailability(value = "isAuthorized")
    public String getAllBooks() {
        return " " + bookRepository.findAll();
    }

}
