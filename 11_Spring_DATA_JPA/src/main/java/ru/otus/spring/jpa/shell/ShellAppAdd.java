package ru.otus.spring.jpa.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.jpa.domain.Book;
import ru.otus.spring.jpa.services.AuthorService;
import ru.otus.spring.jpa.services.BookService;
import ru.otus.spring.jpa.services.GenreService;
import ru.otus.spring.jpa.services.ReviewsService;


@ShellComponent
@RequiredArgsConstructor
public class ShellAppAdd {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final ReviewsService reviewsService;


    @ShellMethod(value = "add author", key = {"addA"})
    public void addAuthor(@ShellOption String fullName) {
        authorService.create(fullName);
    }


    @ShellMethod(value = "add genre", key = {"addG"})
    public void addGenre(@ShellOption String name) {
        genreService.create(name);
    }


    @ShellMethod(value = "add book", key = {"addB"})
    public void addBook(@ShellOption(defaultValue = "Title") String title,
                        @ShellOption(defaultValue = "1") long authorId,
                        @ShellOption(defaultValue = "1") long genreId) {
        Book book = new Book(authorService.getById(authorId), genreService.getGenreById(genreId), title);
        bookService.saveBook(book);
    }


    @ShellMethod(value = "add books Review", key = {"addR"})
    public void addNewReview(@ShellOption(defaultValue = "1") long bookId,
                               @ShellOption(defaultValue = "good Book") String reviewContext) {
        reviewsService.create(bookId, reviewContext + " - " + bookId);
        System.out.println("New review was create.");

    }

}
