package ru.otus.spring.mongoDb.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.mongoDb.domain.Author;
import ru.otus.spring.mongoDb.domain.Genre;
import ru.otus.spring.mongoDb.domain.Note;
import ru.otus.spring.mongoDb.services.AuthorService;
import ru.otus.spring.mongoDb.services.BookService;
import ru.otus.spring.mongoDb.services.GenreService;
import ru.otus.spring.mongoDb.services.NoteService;
import ru.otus.spring.mongoDb.utils.Utils;

import java.util.List;


@ShellComponent
@RequiredArgsConstructor
public class ShellAppGet {
    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookService bookService;
    private final NoteService noteService;


    @ShellMethod(value = "get author", key = {"getA"})
    public String getAuthorById(@ShellOption(defaultValue = "Pushkin") String name) {
        Author author = authorService.findByName(name);
        return author.toString();
    }


    @ShellMethod(value = "get all all Authors", key = {"getAs"})
    public String getAuthors() {
        return Utils.stringFromList(authorService.findAll());
    }

    @ShellMethod(value = "get genre by ID", key = {"getG"})
    public String getGenreById(@ShellOption(defaultValue = "Novel") String name) {
        Genre genre = genreService.findByName(name);
        return genre.toString();
    }

    @ShellMethod(value = "get all all Authors", key = {"getGs"})
    public String getAllGenres() {
        return Utils.stringFromList(genreService.findAll());
    }

    @ShellMethod(value = "get book", key = {"getB"})
    public String getBookById(@ShellOption(defaultValue = "Anna Karenina") String bookName) {
        return bookService.findByName(bookName).toString();
    }

    @ShellMethod(value = "get all books", key = {"getBs"})
    public String getBooks()  {
        return Utils.stringFromList(bookService.findAll());
    }

    @ShellMethod(value = "Show all comments for book", key = "getBCs")
    public String showBookComments(@ShellOption(defaultValue = "Anna Karenina") String bookName) {
        List<Note> notes = noteService.findNotesByBookName(bookName);
        return Utils.stringFromList(notes);
    }

    @ShellMethod(value = "Find by author", key = "getBooksByA")
    public String findBooksByAuthor(@ShellOption(defaultValue = "Tolstoy") String name) {
        return Utils.stringFromList(bookService.findByAuthor(name));
    }

    @ShellMethod(value = "Find by genre", key = "getBooksByG")
    public String findBooksByGenre(@ShellOption(defaultValue = "Novel") String name) {
        return Utils.stringFromList(bookService.findByGenre(name));
    }

}
