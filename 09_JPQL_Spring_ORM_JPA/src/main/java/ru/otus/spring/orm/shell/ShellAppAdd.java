package ru.otus.spring.orm.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.orm.domain.Book;
import ru.otus.spring.orm.services.AuthorService;
import ru.otus.spring.orm.services.BookService;
import ru.otus.spring.orm.services.GenreService;
import ru.otus.spring.orm.services.NoteService;


@ShellComponent
@RequiredArgsConstructor
public class ShellAppAdd {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final NoteService notesService;


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
        Book book = new Book(0L, authorService.getById(authorId), genreService.getGenreById(genreId), title);
        bookService.saveBook(book);
    }


    @ShellMethod(value = "add books Note", key = {"addN"})
    public void addNewNote(@ShellOption(defaultValue = "1") long bookId,
                               @ShellOption(defaultValue = "good Book") String noteContext) {
        notesService.create(bookId, noteContext + " - " + bookId);
        System.out.println("New note was create.");

    }

}
