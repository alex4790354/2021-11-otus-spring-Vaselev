package ru.otus.pk.spring.repository;

import reactor.core.publisher.Flux;
import ru.otus.pk.spring.domain.Author;
import ru.otus.pk.spring.domain.Genre;

public interface BookRepositoryCustom {
    Flux<Author> findAllAuthors();

    Flux<Genre> findAllGenres();
}
