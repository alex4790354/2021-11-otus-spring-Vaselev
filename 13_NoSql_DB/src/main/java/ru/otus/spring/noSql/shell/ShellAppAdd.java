package ru.otus.spring.noSql.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.noSql.domain.Book;
import ru.otus.spring.noSql.service.AuthorService;
import ru.otus.spring.noSql.service.BookService;
import ru.otus.spring.noSql.service.GenreService;


@ShellComponent
@RequiredArgsConstructor
public class ShellAppAdd {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;



    @ShellMethod(value = "add author", key = {"addA"})
    public void addAuthor(@ShellOption(defaultValue = "New Author-name") String fullName) {
        authorService.create(fullName);
    }


    @ShellMethod(value = "add genre", key = {"addG"})
    public void addGenre(@ShellOption(defaultValue = "New Genre-name") String name) {
        genreService.create(name);
    }


    @ShellMethod(value = "add book", key = {"addB"})
    public void addBook(@ShellOption(defaultValue = "Title") String title,
                        @ShellOption(defaultValue = "1") String authorId,
                        @ShellOption(defaultValue = "1") String genreId) {

        bookService.addBook(title, authorId, genreId);
    }


    @ShellMethod(value = "add books comment", key = {"addBC"})
    @Transactional
    public void addBooksComment(@ShellOption(defaultValue = "Cool title 01") String title,
                                @ShellOption(defaultValue = "Cool Book") String commentString) {
        bookService.addBookComment(title, commentString);
    }

}
