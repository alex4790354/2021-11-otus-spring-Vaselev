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

    @ShellMethod(value = "get Note by ID", key = {"getN"})
    public void getNoteById(@ShellOption(defaultValue = "1") long id) {
        Note note = notesService.getNoteById(id);
        System.out.println(note);
    }

    @ShellMethod(value = "get all notes", key = {"getRs"})
    public void getAllNote()  {
        List<Note> notes = notesService.getAllNote();
        System.out.println(notes);
    }

}
