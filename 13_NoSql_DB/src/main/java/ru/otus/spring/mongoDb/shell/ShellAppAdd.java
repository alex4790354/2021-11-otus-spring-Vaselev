package ru.otus.spring.mongoDb.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.mongoDb.services.AuthorService;
import ru.otus.spring.mongoDb.services.BookService;
import ru.otus.spring.mongoDb.services.GenreService;
import ru.otus.spring.mongoDb.services.NoteService;


@ShellComponent
@RequiredArgsConstructor
public class ShellAppAdd {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final NoteService noteService;

    @ShellMethod(value = "add author", key = {"addA"})
    public String addAuthor(@ShellOption(defaultValue = "New Author-name") String fullName) {
        String id = authorService.create(fullName);
        return String.format("Author has been created with id = %s", id);
    }

    @ShellMethod(value = "add genre", key = {"addG"})
    public String addGenre(@ShellOption(defaultValue = "New Genre-name") String name) {
        String id = genreService.create(name);
        return String.format("Genre has been created with id = %s", id);
    }

    @ShellMethod(value = "add book", key = {"addB"})
    public String addBook(@ShellOption(defaultValue = "Fairy tales") String bookName,
                        @ShellOption(defaultValue = "Tolstoy") String authorName,
                        @ShellOption(defaultValue = "Fantasy") String genreName) {
        String id = bookService.create(bookName, authorName, genreName);
        return String.format("Book has been created with id = %s", id);
    }


    @ShellMethod(value = "Add comment for book", key = "addBookComment")
    public String addBookComment(@ShellOption(defaultValue = "The Golden Cockerel") String bookName,
                                 @ShellOption(defaultValue = "Very good cool review") String content){
        String id = noteService.addComment(bookName, content);
        return String.format("Comment has been successfully created with id = %s", id);
    }


}
