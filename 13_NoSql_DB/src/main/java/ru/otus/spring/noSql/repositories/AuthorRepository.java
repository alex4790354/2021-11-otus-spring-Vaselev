package ru.otus.spring.noSql.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.noSql.domain.Author;

import java.util.List;
import java.util.Optional;


public interface AuthorRepository extends CrudRepository<Author, Long> {

    Optional<Author> findById(String id);

    Optional<Author> findByName(String fullName);

    List<Author> findAll();

}
