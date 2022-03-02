package ru.otus.spring.jquery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.jquery.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
