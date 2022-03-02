package ru.otus.spring.mvc.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.mvc.domain.Author;


public interface AuthorRepository extends JpaRepository<Author, Long> {


}
