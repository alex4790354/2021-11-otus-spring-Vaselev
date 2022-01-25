package ru.otus.spring.noSql.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.noSql.domain.Author;


public interface AuthorRepository extends JpaRepository<Author, Long> {


}
