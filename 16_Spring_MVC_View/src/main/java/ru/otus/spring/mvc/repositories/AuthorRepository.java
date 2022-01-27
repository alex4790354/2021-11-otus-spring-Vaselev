package ru.otus.spring.jpa.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.jpa.domain.Author;


public interface AuthorRepository extends JpaRepository<Author, Long> {


}
