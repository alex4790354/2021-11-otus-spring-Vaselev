package ru.otus.spring.webflux.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.spring.webflux.domain.Author;
import ru.otus.spring.webflux.repository.BookRepository;

@RequiredArgsConstructor
@RestController
public class AuthorController {

    private final BookRepository repository;

    @GetMapping("/authors")
    public Flux<Author> finAll() {
        return repository.findAllAuthors();
    }
}
