package ru.otus.spring.mongoDb.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.mongoDb.domain.Genre;
import ru.otus.spring.mongoDb.repository.GenreRepository;
import ru.otus.spring.mongoDb.services.GenreService;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public String create(String name) {
        return genreRepository.save(new Genre(name)).getId();
    }

    @Override
    public void update(String oldName, String newName) {
        Genre genre = findByName(oldName);
        genre.setName(newName);
        genreRepository.save(genre);
    }

    @Override
    public Genre findByName(String name) {
        return Optional.ofNullable(genreRepository.findByName(name))
                       .orElseThrow(() -> new RuntimeException(String.format("Genre with name '%s' not found", name)));
    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public void deleteByName(String name) {
        // TODO: add book delete. Or block if exist.
        genreRepository.deleteByName(name);
    }

}
