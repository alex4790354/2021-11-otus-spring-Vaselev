package ru.otus.spring.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.jpa.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, Long>  {

    List<Author> findAll();

    Optional<Author> findById(long id);

    Optional<Author> findByName(String authorName);

    void deleteById(Long id);
}
