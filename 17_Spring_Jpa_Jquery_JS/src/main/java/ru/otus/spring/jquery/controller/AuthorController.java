package ru.otus.spring.jquery.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.pk.spring.domain.Author;
import ru.otus.pk.spring.service.AuthorService;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class AuthorController {

    private final AuthorService service;

    @GetMapping("/authors")
    public List<Author> finAll() {
        return service.findAll();
    }
}
