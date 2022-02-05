package ru.otus.spring.jquery.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.jquery.domain.Genre;
import ru.otus.spring.jquery.exceptions.RequestException;
import ru.otus.spring.jquery.exceptions.ObjectNotFoundException;
import ru.otus.spring.jquery.repository.GenreRepository;
import ru.otus.spring.jquery.service.GenreService;

import java.util.List;

import static java.lang.String.format;
import static org.springframework.util.ObjectUtils.isEmpty;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    public static final String GENRE_NOT_FOUND = "Genre not found!!! id = %s";

    private final GenreRepository repository;

    @Transactional(readOnly = true)
    @Override
    public Long count() {
        return repository.count();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Genre> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Genre findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(format(GENRE_NOT_FOUND, id)));
    }

    @Transactional
    @Override
    public Genre save(Genre genre) {
        validate(genre);
        return repository.save(genre);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        try {
            repository.deleteById(id);
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
