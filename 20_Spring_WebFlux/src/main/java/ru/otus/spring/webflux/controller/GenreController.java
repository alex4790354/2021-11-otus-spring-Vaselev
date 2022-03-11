package ru.otus.spring.webflux.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.spring.webflux.domain.Genre;
import ru.otus.spring.webflux.repository.BookRepository;

@RequiredArgsConstructor
@RestController
public class GenreController {

    private final BookRepository repository;

    @GetMapping("/genres")
    public Flux<Genre> finAll() {
        return repository.findAllGenres();
    }
}
