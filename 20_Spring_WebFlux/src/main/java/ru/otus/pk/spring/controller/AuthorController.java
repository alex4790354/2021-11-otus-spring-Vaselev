package ru.otus.pk.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.pk.spring.domain.Author;
import ru.otus.pk.spring.repository.BookRepository;

@RequiredArgsConstructor
@RestController
public class AuthorController {

    private final BookRepository repository;

    @GetMapping("/authors")
    public Flux<Author> finAll() {
        return repository.findAllAuthors();
    }
}
