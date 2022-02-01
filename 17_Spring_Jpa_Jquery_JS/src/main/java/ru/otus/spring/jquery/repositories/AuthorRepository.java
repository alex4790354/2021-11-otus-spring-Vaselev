package ru.otus.spring.jquery.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.jquery.domain.Author;


public interface AuthorRepository extends JpaRepository<Author, Long> {


}
