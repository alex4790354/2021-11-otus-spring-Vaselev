package ru.otus.spring.jdbc.shell;

import ru.otus.spring.jdbc.customExceptions.DaoException;
import ru.otus.spring.jdbc.dao.interfaces.AuthorDao;
import ru.otus.spring.jdbc.dao.interfaces.BookDao;
import ru.otus.spring.jdbc.dao.interfaces.GenreDao;
import ru.otus.spring.jdbc.domain.Author;
import ru.otus.spring.jdbc.domain.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.jdbc.domain.Genre;


@ShellComponent
@RequiredArgsConstructor
public class ApplicationShellCommands {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private String userName;

    
    @ShellMethod(value = "Авторизация для пользования библиотекой.", key = {"l", "login"})
    public String login(@ShellOption(defaultValue = "Incognito") String userName) {
        this.userName = userName;
        return String.format("%s, добро пожаловать в библиотеку.", this.userName);
    }

    
    @ShellMethod(value = "Get count", key = {"c", "count"})
    @ShellMethodAvailability(value = "isAuthorized")
    public String getCount() {
        return " " + bookDao.getCount();
    }

    
    @ShellMethod(value = "Get all", key = {"a", "all"})
    @ShellMethodAvailability(value = "isAuthorized")
    public String getAll() {
        return " " + bookDao.getAll();
    }

    
    @ShellMethod(value = "getById", key = {"id", "bookById"})
    @ShellMethodAvailability(value = "isAuthorized")
    public String getById(long id) {
        return " " + bookDao.getById(id);
    }

    
    @ShellMethod(value = "Delete By Id", key = {"d", "dellById"})
    @ShellMethodAvailability(value = "isAuthorized")
    public String deleteById(long id) {
        bookDao.deleteById(id);
        return "Book deleted";
    }

    
    @ShellMethod(value = "Update book name by ID", key = {"u", "update"})
    @ShellMethodAvailability(value = "isAuthorized")
    public String updateById(long id, String newName) {
        bookDao.updateNameById(id, newName);
        return "Update finished.";
    }


    @ShellMethod(value = "Insert new book", key = {"in", "insert"})
    @ShellMethodAvailability(value = "isAuthorized")
    public String insert(long authorId, long genreId, String bookName) throws DaoException {
        Author author = authorDao.getById(authorId);
        Genre genre = genreDao.getById(genreId);
        Book book = new Book(author, genre, bookName);
        bookDao.insert(book);
        return "insert finished.";
    }


    private Availability isAuthorized() {
        return this.userName == null? Availability.unavailable("Сначала авторизуйтесь"): Availability.available();
    }

}
