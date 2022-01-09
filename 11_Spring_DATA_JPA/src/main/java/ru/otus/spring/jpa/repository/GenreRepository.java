package ru.otus.spring.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.jpa.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends CrudRepository<Genre, Long> {

    List<Genre> findAll();

    Optional<Genre> findById(Long id);

    Optional<Genre> findById(String genreName);

    void deleteById(Long id);
}
