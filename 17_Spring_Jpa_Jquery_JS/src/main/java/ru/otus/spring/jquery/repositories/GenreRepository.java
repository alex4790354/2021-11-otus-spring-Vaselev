package ru.otus.spring.jquery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.jquery.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {


}
