package ru.otus.spring.orm.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.orm.domain.Author;
import ru.otus.spring.orm.domain.Book;
import ru.otus.spring.orm.domain.Genre;
import ru.otus.spring.orm.domain.Note;
import ru.otus.spring.orm.services.AuthorService;
import ru.otus.spring.orm.services.BookService;
import ru.otus.spring.orm.services.GenreService;
import ru.otus.spring.orm.services.NoteService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ShellAppGet {
    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookService bookService;
    private final NoteService notesService;


    @ShellMethod(value = "get author", key = {"getA"})
    public Author getAuthorById(@ShellOption(defaultValue = "1") long id) {
        return authorService.getById(id);
    }


    @ShellMethod(value = "get all all Authors", key = {"getAs"})
    public List<Author> getAuthors() {
        return authorService.getAll();
    }

    @ShellMethod(value = "get genre by ID", key = {"getG"})
    public Genre getGenreById(@ShellOption(defaultValue = "1") long id) {
        return genreService.getGenreById(id);
    }

    @ShellMethod(value = "get all all Authors", key = {"getGs"})
    public List<Genre> getAllGenres() {
         return genreService.getGenres();
    }

    @ShellMethod(value = "get book", key = {"getB"})
    public Book getBookById(@ShellOption(defaultValue = "1") long id) {
        return bookService.getBookById(id);
    }

    @ShellMethod(value = "get all books", key = {"getBs"})
    public List<Book> getBooks()  {
        return bookService.getAllBooks();
    }

    @ShellMethod(value = "get Note by ID", key = {"getN"})
    public Note getNoteById(@ShellOption(defaultValue = "1") long id) {
        return notesService.getNoteById(id);
    }

    @ShellMethod(value = "get all notes", key = {"getNs"})
    public List<Note> getAllNote()  {
         return notesService.getAllNote();
    }

}
