package ru.otus.spring.acl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.acl.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {


}
