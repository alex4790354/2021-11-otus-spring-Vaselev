package ru.otus.spring.batch.batch;

import org.springframework.stereotype.Service;
import ru.otus.spring.batch.domain.mongo.*;
import ru.otus.spring.batch.domain.sql.*;

@Service
public class TransformationService {

    public Book transformBook(MongoBook mongoBook) {
        MongoAuthor mongoAuthor = mongoBook.getAuthor();
        MongoGenre mongoGenre = mongoBook.getGenre();

        return new Book(null,
                mongoBook.getName(),
                new Author(null, mongoAuthor.getFirstName(), mongoAuthor.getLastName(), mongoAuthor.getId()),
                new Genre(null, mongoGenre.getName(), mongoGenre.getId()),
                mongoBook.getId());
    }

    public Comment transformComment(MongoComment mongoComment) {
        MongoBook mongoBook = mongoComment.getBook();

        return new Comment(null,
                mongoComment.getText(),
                new Book(null, null, null, null, mongoBook.getId()));
    }
}
