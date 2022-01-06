package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.service.interfaces.Localization;
import ru.otus.spring.service.interfaces.QuestionsService;
import ru.otus.spring.service.interfaces.StudentLoginService;


@ShellComponent
@RequiredArgsConstructor
public class ApplicationEventsCommands {


    private final StudentLoginService stLoginService;
    private final QuestionsService questionsService;
    private final Localization localization;


    @ShellMethod(value = "Авторизация для студентов перед сдачей экзамена", key = {"l", "login"})
    public String login(@ShellOption(defaultValue = "Incognito") String studentName) {
        stLoginService.setStudentName(studentName);
        return localization.getPropertiesValue("exam.welcome", stLoginService.getStudentName());
    }

    @ShellMethod(value = "Прохождение теста", key = {"t", "test"})
    @ShellMethodAvailability(value = "isPublishEventCommandAvailable")
    public void studentTest() {
        questionsService.takeExam();
    }

    private Availability isAuthorized() {
        return stLoginService.getStudentName() == null? Availability.unavailable("Сначала авторизуйтесь"): Availability.available();
    }

}
