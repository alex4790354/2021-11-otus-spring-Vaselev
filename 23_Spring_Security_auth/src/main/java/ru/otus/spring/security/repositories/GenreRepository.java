package ru.otus.spring.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.security.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {


}
