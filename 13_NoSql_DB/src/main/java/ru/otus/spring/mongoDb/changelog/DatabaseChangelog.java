package ru.otus.spring.mongoDb.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.spring.mongoDb.domain.Author;
import ru.otus.spring.mongoDb.domain.Book;
import ru.otus.spring.mongoDb.domain.Genre;
import ru.otus.spring.mongoDb.domain.Note;
import ru.otus.spring.mongoDb.repository.AuthorRepository;
import ru.otus.spring.mongoDb.repository.BookRepository;
import ru.otus.spring.mongoDb.repository.NoteRepository;
import ru.otus.spring.mongoDb.repository.GenreRepository;

import java.util.List;

@ChangeLog
public class DatabaseChangelog {

    private final Author author1 = new Author("Tolstoy");
    private final Author author2 = new Author("Pushkin");
    private final Genre genre1 = new Genre("Novel");
    private final Genre genre2 = new Genre("Fantasy");
    private final Book book1 = new Book("Anna Karenina", List.of(author1, author2), List.of(genre1));
    private final Book book2 = new Book("The Golden Cockerel", List.of(author2), List.of(genre2));
    private final Book book3 = new Book("Gore", List.of(author1, author2), List.of(genre1));


    @ChangeSet(order = "001", id = "dropDb", author = "AlexV", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "initAuthors", author = "AlexV")
    public void initAuthors(AuthorRepository repository)
    {
        repository.save(author1);
        repository.save(author2);
    }
        @ChangeSet(order = "003", id = "initGenres", author = "AlexV")
    public void initGenres(GenreRepository repository)
    {
        repository.save(genre1);
        repository.save(genre2);
    }

    @ChangeSet(order = "004", id = "initBooks", author = "AlexV")
    public void initBooks(BookRepository repository)
    {
        repository.save(book1);
        repository.save(book2);
        repository.save(book3);
    }

    @ChangeSet(order = "005", id = "initComments", author = "AlexV")
    public void initComments(BookRepository bookRepository, NoteRepository repository)
    {
        String bookId1 = bookRepository.findByName("Anna Karenina").getId();
        String bookId2 = bookRepository.findByName("The Golden Cockerel").getId();

        repository.save( new Note(bookId1, "Very good Anna") );
        repository.save( new Note(bookId1, "Just cool Anna") );
        repository.save( new Note(bookId2, "Cockerel") );
        repository.save( new Note(bookId2, "Good Cockerel") );
        repository.save( new Note(bookId2, "Very good Anna") );
    }

}
