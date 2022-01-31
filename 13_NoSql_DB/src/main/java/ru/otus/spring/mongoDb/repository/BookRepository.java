package ru.otus.spring.mongoDb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import ru.otus.spring.mongoDb.domain.Book;

import java.util.List;

@Component
public interface BookRepository extends MongoRepository<Book, String> {

    Book findByName(String bookName);
    void deleteByName(String bookName);
    List<Book> findAllByAuthors(String authorId);
    List<Book> findAllByGenres(String genreId);

}
