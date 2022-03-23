package ru.otus.spring.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import ru.otus.spring.batch.domain.sql.Genre;

import java.util.Collection;
import java.util.Set;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Set<Genre> findByMongoIdIn(@Param("mongoIds") Collection<String> mongoIds);
}
