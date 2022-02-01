package ru.otus.spring.jquery.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.jquery.domain.Book;
import ru.otus.spring.jquery.services.AuthorService;
import ru.otus.spring.jquery.services.BookService;
import ru.otus.spring.jquery.services.GenreService;
import ru.otus.spring.jquery.services.NoteService;


@RequiredArgsConstructor
@ShellComponent
public class ShellAppUpdate {

    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookService bookService;
    private final NoteService reviewsService;

    @ShellMethod(value = "update author", key = {"updA"})
    public void updateAuthorName(@ShellOption(defaultValue = "1") long id,
                                 @ShellOption(defaultValue = "New authour-1") String newName) {
        authorService.save(id, newName);
        System.out.println("Author updates. New author: ");
    }

    @ShellMethod(value = "update genre", key = {"updG"})
    public void updateGenreName(@ShellOption(defaultValue = "1") long id,
                                @ShellOption(defaultValue = "new Genre name-1") String newName) {
        genreService.save(id, newName);
        System.out.println("Genre updated");
    }

    @ShellMethod(value = "update book", key = {"updB"})
    public void updateBookTitle(@ShellOption(defaultValue = "1") long id,
                               @ShellOption(defaultValue = "new cool book title-1") String newName) {
        Book book = bookService.findById(id);
        book.setTitle(newName);
        bookService.saveBook(book);
        System.out.println("book updated. New book title: " + book.getTitle());
    }

    @ShellMethod(value = "update review", key = {"updN"})
    public void updateNoteContext(@ShellOption(defaultValue = "1") long id,
                                    @ShellOption(defaultValue = "new cool review context-1") String newContext) {
        reviewsService.save(id, newContext);
        System.out.println("Note context was updated.");
    }

}
