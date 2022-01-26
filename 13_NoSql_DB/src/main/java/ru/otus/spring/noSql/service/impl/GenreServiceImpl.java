package ru.otus.spring.noSql.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.spring.noSql.domain.Genre;
import ru.otus.spring.noSql.repositories.GenreRepository;
import ru.otus.spring.noSql.service.GenreService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public List<Genre> getGenres() {
        return genreRepository.findAll();
    }

    @Override
    public Genre getGenreByName(String name) {
        return genreRepository.findByName(name).orElseThrow();
    }

    @Override
    public String create(String name) {
        return genreRepository.save(new Genre(name)).getId();
    }

    @Override
    public void update(String oldName, String name) {
        Genre genre = genreRepository.findByName(oldName).orElseThrow();
        genre.setName(name);
        genreRepository.save(genre);
    }

    @Override
    public void delete(String name) {
        Genre genre = genreRepository.findByName(name).orElseThrow();
        genreRepository.delete(genre);
    }
}
