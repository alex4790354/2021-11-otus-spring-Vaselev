package ru.otus.spring.orm.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.orm.services.AuthorService;
import ru.otus.spring.orm.services.BookService;
import ru.otus.spring.orm.services.GenreService;
import ru.otus.spring.orm.services.ReviewsService;


@RequiredArgsConstructor
@ShellComponent
public class ShellAppDelete {

    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookService bookService;
    private final ReviewsService reviewsService;


    @ShellMethod(value = "delete author", key = {"delA"})
    public void deleteAuthorById(@ShellOption(defaultValue = "1") long authorId) {
        authorService.delete(authorId);
        System.out.println("Author with id=" + authorId + " deleted.");
    }


    @ShellMethod(value = "delete genre", key = {"delG"})
    public void deleteGenreById(@ShellOption(defaultValue = "1") long genreId) {
        genreService.delete(genreId);
        System.out.println("Genre with id = " + genreId + " was deleted");
    }


    @ShellMethod(value = "delete book", key = {"delB"})
    public void deleteBookById(@ShellOption(defaultValue = "1") long bookId) {
        bookService.deleteBook(bookId);
        System.out.println("Book with bookId = " + bookId + " was deleted");
    }


    @ShellMethod(value = "delete books Reviews", key = {"delR"})
    public void deleteBooksReviews(@ShellOption long reviewId) {
        reviewsService.delete(reviewId);
        System.out.println("Review with ID = " + reviewId + " deleted.");
    }

}
