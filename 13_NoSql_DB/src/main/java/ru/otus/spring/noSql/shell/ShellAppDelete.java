package ru.otus.spring.noSql.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.noSql.service.AuthorService;
import ru.otus.spring.noSql.service.BookService;
import ru.otus.spring.noSql.service.GenreService;


@RequiredArgsConstructor
@ShellComponent
public class ShellAppDelete {

    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookService bookService;


    @ShellMethod(value = "delete author", key = {"delA"})
    public void deleteAuthorById(@ShellOption(defaultValue = "1") String authorId) {
        authorService.delete(authorId);
        System.out.println("Author with id=" + authorId + " deleted.");
    }

    @ShellMethod(value = "delete genre", key = {"delG"})
    public void deleteGenreById(@ShellOption(defaultValue = "1") String genreId) {
        genreService.delete(genreId);
        System.out.println("Genre with id = " + genreId + " was deleted");
    }

    @ShellMethod(value = "delete book", key = {"delB"})
    public void deleteBookById(@ShellOption(defaultValue = "1") String bookId) {
        bookService.deleteBook(bookId);
        System.out.println("Book with bookId = " + bookId + " was deleted");
    }

    @ShellMethod(value = "delete books Notes", key = {"delN"})
    public void deleteBooksNotes(@ShellOption String noteId) {
        bookService.deleteBookComment(noteId);
        System.out.println("Note with ID = " + noteId + " was deleted.");
    }

}
