package ru.otus.spring.shell;

import ru.otus.spring.events.EventsPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;


@ShellComponent
@RequiredArgsConstructor
public class ApplicationEventsCommands {

    private final EventsPublisher eventsPublisher;
    private String name;

    @ShellMethod(value = "Авторизация для студентов перед сдачей экзамена", key = {"l", "login"})
    public String login(@ShellOption(defaultValue = "Incognito") String Name) {
        this.name = name;
        return String.format("%s, добро пожаловать на сдачу теста. (test или help для начала прохождения)", name);
    }

    @ShellMethod(value = "Прохождение теста", key = {"t", "test"})
    @ShellMethodAvailability(value = "isPublishEventCommandAvailable")
    public String publishEvent() {
        eventsPublisher.publish();
        return "Экзамен проведен. ('test' для повторного прохождения или 'exit' для выхода). Для студента: " + this.name;
    }

    private Availability isPublishEventCommandAvailable() {
        return this.name == null? Availability.unavailable("Сначала авторизуйтесь"): Availability.available();
    }
}
