package ru.otus.spring.webflux.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import org.bson.types.ObjectId;
import ru.otus.spring.webflux.domain.Author;
import ru.otus.spring.webflux.domain.Book;
import ru.otus.spring.webflux.domain.Comment;
import ru.otus.spring.webflux.domain.Genre;

import java.util.List;

@ChangeLog(order = "001")
public class InitMongoDBDataChangeLog {

    @ChangeSet(order = "000", id = "init", author = "pk", runAlways = true)
    public void init(MongockTemplate template) {
        Author author1 = new Author(ObjectId.get().toString(), "AuthorF1", "AuthorL1");
        Author author2 = new Author(ObjectId.get().toString(), "AuthorF2", "AuthorL2");

        Genre genre1 = new Genre(ObjectId.get().toString(), "Genre1");
        Genre genre2 = new Genre(ObjectId.get().toString(), "Genre2");

        Book book1 = template.save(new Book(null, "Book1", author1, genre1));
        Book book2 = template.save(new Book(null, "Book2", author2, genre2));
        template.save(new Book(null, "Book3", author1, genre1));

        template.insertAll(List.of(
                new Comment(null, "Comment1", book1),
                new Comment(null, "Comment2", book2),
                new Comment(null, "Comment3", book1)));
    }
}
