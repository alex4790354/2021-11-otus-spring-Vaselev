package ru.otus.spring.noSql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.noSql.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {


}
