package ru.otus.spring.mongoDb.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.mongoDb.domain.Book;
import ru.otus.spring.mongoDb.services.AuthorService;
import ru.otus.spring.mongoDb.services.BookService;
import ru.otus.spring.mongoDb.services.GenreService;
import ru.otus.spring.mongoDb.services.NoteService;


@RequiredArgsConstructor
@ShellComponent
public class ShellAppUpdate {

    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookService bookService;
    private final NoteService noteService;


    @ShellMethod(value = "update author", key = {"updA"})
    public String updateAuthorName(@ShellOption(defaultValue = "Tolstoy") String oldName,
                                   @ShellOption(defaultValue = "Tolstoy-Updated") String newName) {
        authorService.update(oldName, newName);
        return "Author has been successfully updated";
    }

    @ShellMethod(value = "update genre", key = {"updG"})
    public String updateGenreName(@ShellOption(defaultValue = "Fantasy") String oldName,
                                @ShellOption(defaultValue = "Fantasy-Updated") String newName) {
        genreService.update(oldName, newName);
        return "Genre has been successfully updated";
    }

    @ShellMethod(value = "update book", key = {"updB"})
    public String updateBookTitle(@ShellOption(defaultValue = "Anna Karenina") String oldName,
                               @ShellOption(defaultValue = "Anna Karenina-Updated") String newName) {
        bookService.update(oldName, newName);
        return "Book has been successfully updated";
    }

}
