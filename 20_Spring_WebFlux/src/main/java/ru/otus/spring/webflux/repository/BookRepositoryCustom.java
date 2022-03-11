package ru.otus.spring.webflux.repository;

import reactor.core.publisher.Flux;
import ru.otus.spring.webflux.domain.Author;
import ru.otus.spring.webflux.domain.Genre;

public interface BookRepositoryCustom {
    Flux<Author> findAllAuthors();

    Flux<Genre> findAllGenres();
}
