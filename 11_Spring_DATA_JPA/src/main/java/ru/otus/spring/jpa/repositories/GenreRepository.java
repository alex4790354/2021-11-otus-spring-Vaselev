package ru.otus.spring.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.jpa.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {


}
