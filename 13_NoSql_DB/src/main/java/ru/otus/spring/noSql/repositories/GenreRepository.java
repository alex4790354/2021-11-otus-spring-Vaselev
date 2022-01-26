package ru.otus.spring.noSql.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.noSql.domain.Genre;
import java.util.List;
import java.util.Optional;


public interface GenreRepository extends CrudRepository<Genre, Long> {

    Optional<Genre> findById(String id);

    Optional<Genre> findByName(String id);

    List<Genre> findAll();

}
