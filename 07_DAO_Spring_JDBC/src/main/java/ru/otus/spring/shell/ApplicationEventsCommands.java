package ru.otus.spring.shell;

import ru.otus.spring.customExceptions.DaoException;
import ru.otus.spring.dao.interfaces.BookDao;
import ru.otus.spring.domain.Book;
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
    private final BookDao bookDao;
    private String userName;

    @ShellMethod(value = "Авторизация для студентов перед сдачей экзамена", key = {"l", "login"})
    public String login(@ShellOption(defaultValue = "Incognito") String userName) {
        this.userName = userName;
        return String.format("%s, добро пожаловать на сдачу теста. (test или help для начала прохождения)", this.userName);
    }

    @ShellMethod(value = "Get count", key = {"c", "count"})
    @ShellMethodAvailability(value = "isPublishEventCommandAvailable")
    public String getCount() {
        return " " + bookDao.getCount();
    }

    @ShellMethod(value = "Get all", key = {"a", "all"})
    @ShellMethodAvailability(value = "isPublishEventCommandAvailable")
    public String getAll() {
        return " " + bookDao.getAll();
    }

    @ShellMethod(value = "getById", key = {"id", "bookById"})
    @ShellMethodAvailability(value = "isPublishEventCommandAvailable")
    public String getById(long id) {
        return " " + bookDao.getById(id);
    }

    @ShellMethod(value = "Delete By Id", key = {"d", "dellById"})
    @ShellMethodAvailability(value = "isPublishEventCommandAvailable")
    public String deleteById(long id) {
        bookDao.deleteById(id);
        return "Book deleted";
    }

    @ShellMethod(value = "Update book name by ID", key = {"u", "update"})
    @ShellMethodAvailability(value = "isPublishEventCommandAvailable")
    public String updateById(long id, String newName) {
        bookDao.updateById(id, newName);
        return "Update finished.";
    }

    @ShellMethod(value = "Insert new book", key = {"in", "insert"})
    @ShellMethodAvailability(value = "isPublishEventCommandAvailable")
    public String insert(Book book) throws DaoException {
        bookDao.insert(book);
        return "insert finished.";
    }

    private Availability isPublishEventCommandAvailable() {
        return this.userName == null? Availability.unavailable("Сначала авторизуйтесь"): Availability.available();
    }
}
