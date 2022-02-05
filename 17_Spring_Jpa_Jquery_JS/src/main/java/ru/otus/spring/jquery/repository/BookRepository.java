package ru.otus.spring.jquery.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.jquery.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @EntityGraph(value = "book-author-genre")
    @Override
    List<Book> findAll();

    @EntityGraph(value = "book-author-genre")
    @Override
    Optional<Book> findById(Long id);

    @EntityGraph(value = "book-author-genre")
    List<Book> findByAuthorId(Long authorId);

    @EntityGraph(value = "book-author-genre")
    List<Book> findByGenreId(Long genreId);
}
