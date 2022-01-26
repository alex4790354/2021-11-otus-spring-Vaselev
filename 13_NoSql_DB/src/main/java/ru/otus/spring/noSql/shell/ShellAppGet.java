package ru.otus.spring.noSql.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.noSql.domain.Author;
import ru.otus.spring.noSql.domain.Book;
import ru.otus.spring.noSql.domain.Genre;
import ru.otus.spring.noSql.domain.Note;
import ru.otus.spring.noSql.service.AuthorService;
import ru.otus.spring.noSql.service.BookService;
import ru.otus.spring.noSql.service.GenreService;

import java.util.List;


@ShellComponent
@RequiredArgsConstructor
public class ShellAppGet {
    private final AuthorService authorService;
    private final BookService bookService;
    private final GenreService genreService;


    @ShellMethod(value = "get author", key = {"getA"})
    public void getAuthorById(@ShellOption(defaultValue = "????") String name) {
        Author author = authorService.getByName(name);
        System.out.println(author);
    }


    @ShellMethod(value = "get all all Authors", key = {"getAs"})
    public void getAuthors() {
        List<Author> authors = authorService.getAll();
        System.out.println(authors);
    }

    @ShellMethod(value = "get genre by ID", key = {"getG"})
    public void getGenreById(@ShellOption(defaultValue = "1") String name) {
        Genre genre = genreService.getGenreByName(name);
        System.out.println(genre);
    }

    @ShellMethod(value = "get all all Authors", key = {"getGs"})
    public void getAllGenres() {
        List<Genre> genres = genreService.getGenres();
        System.out.println(genres);
    }

    @ShellMethod(value = "get book", key = {"getB"})
    public void getBookById(@ShellOption(defaultValue = "1") String title) {
        Book book = bookService.getBookByTitle(title);
        System.out.println(book);
    }

    @ShellMethod(value = "get all books", key = {"getBs"})
    public void getBooks()  {
        List<Book> books = bookService.getAllBooks();
        System.out.println(books);
    }

    @ShellMethod(value = "get all notes", key = {"getNs"})
    public void getAllNotes()  {
        List<Note> notes = bookService.getAllComments();
        System.out.println(notes);
    }

    @ShellMethod(value = "get Note by book title", key = {"getN"})
    public void getNoteById(@ShellOption(defaultValue = "???? ") String bookTitle) {
        List<Note> booksNotes = bookService.getBookComments(bookTitle);
        System.out.println(booksNotes);
    }

}
