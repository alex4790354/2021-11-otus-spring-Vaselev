package ru.otus.spring.jquery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.jquery.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
