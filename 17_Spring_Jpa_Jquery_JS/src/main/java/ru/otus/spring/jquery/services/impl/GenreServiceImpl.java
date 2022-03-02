package ru.otus.spring.jquery.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.jquery.domain.Genre;
import ru.otus.spring.jquery.exceptions.RequestException;
import ru.otus.spring.jquery.exceptions.ObjectNotFoundException;
import ru.otus.spring.jquery.repository.GenreRepository;
import ru.otus.spring.jquery.services.GenreService;
import java.util.List;
import static java.lang.String.format;
import static org.springframework.util.ObjectUtils.isEmpty;


@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    public static final String GENRE_NOT_FOUND = "Genre not found. Id = %s";
    private final GenreRepository genreRepository;

    @Transactional(readOnly = true)
    @Override
    public Long count() {
        return genreRepository.count();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Genre findById(Long id) {
        return genreRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(format(GENRE_NOT_FOUND, id)));
    }

    @Transactional
    @Override
    public Genre save(Genre genre) {
        validate(genre);
        return genreRepository.save(genre);
    }

    @Transactional
    @Override
    public Genre save(String name) {
        Genre genre = new Genre(0, name);
        return genreRepository.save(genre);
    }

    @Transactional
    @Override
    public Genre save(long id, String name) {
        Genre genre = genreRepository.findById(id).orElse(null);
        if (genre == null) {
            throw new ObjectNotFoundException(format(GENRE_NOT_FOUND, id));
        }
        genre.setName(name);
        return genreRepository.save(genre);
    }


    @Transactional
    @Override
    public void deleteById(Long id) {
        try {
            genreRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ObjectNotFoundException(format(GENRE_NOT_FOUND, id), e);
        }
    }

    private void validate(Genre genre) {
        if (isEmpty(genre.getName())) {
            throw new RequestException("Genre name is null or empty!");
        }
    }
}
