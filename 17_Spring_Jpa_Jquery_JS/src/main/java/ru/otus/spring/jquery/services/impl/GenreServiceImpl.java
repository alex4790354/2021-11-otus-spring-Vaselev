package ru.otus.spring.jquery.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.jquery.customExceptions.DaoException;
import ru.otus.spring.jquery.domain.Genre;
import ru.otus.spring.jquery.repositories.GenreRepository;
import ru.otus.spring.jquery.services.GenreService;

import java.util.List;


@Component
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private static final String GENRE_NOT_EXIST = "Didn't find genre";


    @Transactional(readOnly = true)
    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }


    @Transactional(readOnly = true)
    @Override
    public Genre findById(long id) {
        Genre genreById = genreRepository.findById(id).orElse(null);
        if (genreById != null) {
            return genreById;
        }
        throw new DaoException(GENRE_NOT_EXIST);
    }


    @Transactional
    @Override
    public void save(long id, String name) {
        Genre genre = genreRepository.findById(id).orElse(null);
        if (genre == null) {
            throw new DaoException(GENRE_NOT_EXIST);
        }
        genre.setName(name);
        genreRepository.save(genre);
    }


    @Transactional
    @Override
    public long create(String name) {
        Genre genre = new Genre(0, name);
        return genreRepository.save(genre).getId();
    }


    @Transactional
    @Override
    public void delete(long genreId) {
        Genre genre = genreRepository.findById(genreId).orElse(null);
        if (genre != null) {
            genreRepository.delete(genre);
        }
    }
}
