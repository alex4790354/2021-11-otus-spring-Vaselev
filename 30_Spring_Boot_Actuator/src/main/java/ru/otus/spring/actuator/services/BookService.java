package ru.otus.spring.actuator.services;

import ru.otus.spring.actuator.domain.Book;

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
