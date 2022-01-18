package ru.otus.spring.jpa.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.jpa.domain.Author;
import ru.otus.spring.jpa.domain.Book;
import ru.otus.spring.jpa.domain.Genre;
import ru.otus.spring.jpa.domain.Review;
import ru.otus.spring.jpa.services.AuthorService;
import ru.otus.spring.jpa.services.BookService;
import ru.otus.spring.jpa.services.GenreService;
import ru.otus.spring.jpa.services.ReviewsService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ShellAppGet {
    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookService bookService;
    private final ReviewsService reviewsService;


    @ShellMethod(value = "get author", key = {"getA"})
    public void getAuthorById(@ShellOption(defaultValue = "1") long id) {
        Author author = authorService.getById(id);
        System.out.println(author);
    }


    @ShellMethod(value = "get all all Authors", key = {"getAs"})
    public void getAuthors() {
        List<Author> authors = authorService.getAll();
        System.out.println(authors);
    }

    @ShellMethod(value = "get genre by ID", key = {"getG"})
    public void getGenreById(@ShellOption(defaultValue = "1") long id) {
        Genre genre = genreService.getGenreById(id);
        System.out.println(genre);
    }

    @ShellMethod(value = "get all all Authors", key = {"getGs"})
    public void getAllGenres() {
        List<Genre> genres = genreService.getGenres();
        System.out.println(genres);
    }

    @ShellMethod(value = "get book", key = {"getB"})
    public void getBookById(@ShellOption(defaultValue = "1") long id) {
        Book book = bookService.getBookById(id);
        System.out.println(book);
    }

    @ShellMethod(value = "get all books", key = {"getBs"})
    public void getBooks()  {
        List<Book> books = bookService.getAllBooks();
        System.out.println(books);
    }

    @ShellMethod(value = "get Review by ID", key = {"getR"})
    public void getReviewById(@ShellOption(defaultValue = "1") long id) {
        Review review = reviewsService.getReviewById(id);
        System.out.println(review);
    }

    @ShellMethod(value = "get all reviews", key = {"getRs"})
    public void getAllReviews()  {
        List<Review> reviews = reviewsService.getAllReviews();
        System.out.println(reviews);
    }

}
