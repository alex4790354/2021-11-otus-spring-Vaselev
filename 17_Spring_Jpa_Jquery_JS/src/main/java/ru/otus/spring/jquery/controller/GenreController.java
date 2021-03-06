package ru.otus.spring.jquery.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.jquery.domain.Genre;
import ru.otus.spring.jquery.services.GenreService;
import java.util.List;


@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class GenreController {

    private final GenreService service;

    @GetMapping("/genres")
    public List<Genre> finAll() {
        return service.findAll();
    }

}
