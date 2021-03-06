package ru.otus.spring.acl.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.acl.domain.Book;
import java.util.List;
import java.util.Optional;


public interface BookRepository extends JpaRepository<Book, Long> {

    @EntityGraph(value = "book-author-genre")
    @Override
    List<Book> findAll();


    @EntityGraph(value = "book-author-genre")
    @Override
    Optional<Book> findById(Long id);


}
