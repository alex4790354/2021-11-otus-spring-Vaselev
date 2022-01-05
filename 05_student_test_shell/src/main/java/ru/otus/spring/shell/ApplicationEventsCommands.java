package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.service.interfaces.QuestionsService;
import ru.otus.spring.service.interfaces.StudentLoginService;


@ShellComponent
@RequiredArgsConstructor
public class ApplicationEventsCommands {


    private final StudentLoginService stLoginService;
    private final QuestionsService questionsService;


    @ShellMethod(value = "Авторизация для студентов перед сдачей экзамена", key = {"l", "login"})
    public String login(@ShellOption(defaultValue = "Incognito") String studentName) {
        stLoginService.setStudentName(studentName);
        return String.format("%s, добро пожаловать на сдачу теста. (test или help для начала прохождения)", studentName);
    }

    @ShellMethod(value = "Прохождение теста", key = {"t", "test"})
    @ShellMethodAvailability(value = "isPublishEventCommandAvailable")
    public String studentTest() {
        questionsService.takeExam();
        return "Экзамен проведен. ('test' для повторного прохождения или 'exit' для выхода). Для студента: " + stLoginService.getStudentName();
    }

    private Availability isPublishEventCommandAvailable() {
        return stLoginService.getStudentName() == null? Availability.unavailable("Сначала авторизуйтесь"): Availability.available();
    }

}
