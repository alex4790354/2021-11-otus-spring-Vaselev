package ru.otus.spring.mongoDb.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.mongoDb.services.AuthorService;
import ru.otus.spring.mongoDb.services.BookService;
import ru.otus.spring.mongoDb.services.GenreService;
import ru.otus.spring.mongoDb.services.NoteService;


@RequiredArgsConstructor
@ShellComponent
public class ShellAppDelete {

    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookService bookService;
    private final NoteService noteService;


    @ShellMethod(value = "Delete comment for book", key = "delBookComment")
    public String deleteBookComment(@ShellOption(defaultValue = "The Golden Cockerel") String bookName,
                                    @ShellOption(defaultValue = "Very good cool review") String content) {
        noteService.deleteComment(bookName, content);
        return "Comment has been successfully deleted";
    }

    @ShellMethod(value = "delete author", key = {"delA"})
    public String deleteAuthor(@ShellOption(defaultValue = "New Author-name") String authorName) {
        authorService.deleteByName(authorName);
        return "Author has been successfully deleted";
    }

    @ShellMethod(value = "delete genre", key = {"delG"})
    public String deleteGenre(@ShellOption(defaultValue = "New Genre-name") String genreName) {
        genreService.deleteByName(genreName);
        return "Genre has been successfully deleted";
    }

    @ShellMethod(value = "delete book", key = {"delB"})
    public String deleteBook(@ShellOption(defaultValue = "Fairy tales") String bookName) {
        bookService.deleteByName(bookName);
        return "Book has been successfully deleted";
    }

}
