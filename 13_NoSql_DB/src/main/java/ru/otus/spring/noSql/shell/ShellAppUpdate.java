package ru.otus.spring.noSql.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.noSql.domain.Book;
import ru.otus.spring.noSql.service.AuthorService;
import ru.otus.spring.noSql.service.BookService;
import ru.otus.spring.noSql.service.GenreService;


@RequiredArgsConstructor
@ShellComponent
public class ShellAppUpdate {

    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookService bookService;


    @ShellMethod(value = "update author", key = {"updA"})
    public void updateAuthorName(@ShellOption(defaultValue = "1") String oldName,
                                 @ShellOption(defaultValue = "New authour-1") String newName) {
        authorService.update(oldName, newName);
        System.out.println("Author updates. New author: ");
    }

    @ShellMethod(value = "update genre", key = {"updG"})
    public void updateGenreName(@ShellOption String oldName,
                                @ShellOption(defaultValue = "new Genre name-1") String newName) {
        genreService.update(oldName, newName);
        System.out.println("Genre updated");
    }

    @ShellMethod(value = "update book", key = {"updB"})
    public void updateBookTitle(@ShellOption(defaultValue = "1") String oldTile,
                               @ShellOption(defaultValue = "new cool book title-1") String newTitle) {
        Book book = bookService.getBookByTitle(oldTile);
        book.setTitle(newTitle);
        bookService.saveBook(book);
        System.out.println("book with title: " + oldTile + " updated. New book title: " + newTitle);
    }

    @ShellMethod(value = "update review", key = {"updN"})
    public void updateNoteContext(@ShellOption(defaultValue = "title1") String bookTitle,
                                  @ShellOption(defaultValue = "prefix") String newValue) {
        bookService.changeBookComment(bookTitle, newValue);
        System.out.println("Note context was updated.");
    }

}
