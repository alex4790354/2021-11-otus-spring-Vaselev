package ru.otus.spring.actuator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.actuator.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
