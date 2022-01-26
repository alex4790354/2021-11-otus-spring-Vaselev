package ru.otus.spring.noSql.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.noSql.domain.Author;
import ru.otus.spring.noSql.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long>, CustomBookRepository {

    Optional<Book> findById(String bookId);

    Optional<Book> findByTitle(String title);

    List<Book> findAll();

    List<Book> findByAuthor(Author author);
}
