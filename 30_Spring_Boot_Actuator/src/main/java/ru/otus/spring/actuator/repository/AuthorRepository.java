package ru.otus.spring.actuator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.actuator.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
