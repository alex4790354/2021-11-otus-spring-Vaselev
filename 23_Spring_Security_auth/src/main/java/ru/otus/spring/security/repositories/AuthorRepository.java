package ru.otus.spring.security.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.security.domain.Author;


public interface AuthorRepository extends JpaRepository<Author, Long> {


}
