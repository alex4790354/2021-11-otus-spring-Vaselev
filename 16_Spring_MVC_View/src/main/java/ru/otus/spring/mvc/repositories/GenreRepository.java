package ru.otus.spring.mvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.mvc.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {


}
