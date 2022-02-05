package ru.otus.spring.jquery.services;

import ru.otus.spring.jquery.domain.Book;
import java.util.List;


public interface BookService {

    Long count();

    List<Book> findAll();

    Book findById(Long id);

    Book save(Book book);

    void deleteById(Long id);

    List<Book> findByAuthorId(Long authorId);

    List<Book> findByGenreId(Long genreId);
}
