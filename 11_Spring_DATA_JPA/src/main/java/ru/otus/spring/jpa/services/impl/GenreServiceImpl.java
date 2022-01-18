package ru.otus.spring.jpa.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.jpa.customExceptions.DaoException;
import ru.otus.spring.jpa.domain.Genre;
import ru.otus.spring.jpa.repositories.GenreRepository;
import ru.otus.spring.jpa.services.GenreService;

import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private static final String GENRE_NOT_EXIST = "Didn't find genre";


    @Transactional
    @Override
    public long create(String name) {
        Genre genre = new Genre(0, name, new ArrayList<>());
        return genreRepository.save(genre).getId();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Genre> getGenres() {
        return genreRepository.findAll();
    }

    @SneakyThrows
    @Transactional(readOnly = true)
    @Override
    public Genre getGenreById(long id) {
        Genre genreById = genreRepository.findById(id).orElse(null);
        if (genreById != null) {
            return genreById;
        }
        throw new DaoException(GENRE_NOT_EXIST, new RuntimeException());
    }

    @SneakyThrows
    @Transactional
    @Override
    public void update(long id, String name) {
        Genre genre = genreRepository.findById(id).orElse(null);
        if (genre == null) {
            throw new DaoException(GENRE_NOT_EXIST, new RuntimeException());
        }
        genre.setName(name);
        genreRepository.save(genre);
    }

    @SneakyThrows
    @Transactional
    @Override
    public void delete(long genreId) {
        Genre genre = genreRepository.findById(genreId).orElse(null);
        if (genre == null) {
            throw new DaoException(GENRE_NOT_EXIST, new RuntimeException());
        }
        genreRepository.delete(genre);
    }
}
