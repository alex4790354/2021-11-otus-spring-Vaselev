package ru.otus.spring.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import ru.otus.spring.batch.domain.sql.Author;

import java.util.Collection;
import java.util.Set;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Set<Author> findByMongoIdIn(@Param("mongoIds") Collection<String> mongoIds);
}
