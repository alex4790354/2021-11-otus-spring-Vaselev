package ru.otus.spring.jquery.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.jquery.domain.Author;
import ru.otus.spring.jquery.exceptions.RequestException;
import ru.otus.spring.jquery.exceptions.ObjectNotFoundException;
import ru.otus.spring.jquery.repository.AuthorRepository;
import ru.otus.spring.jquery.service.AuthorService;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static java.lang.String.join;
import static org.springframework.util.ObjectUtils.isEmpty;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    public static final String AUTHOR_NOT_FOUND = "Author not found!!! id = %s";

    private final AuthorRepository repository;

    @Transactional(readOnly = true)
    @Override
    public Long count() {
        return repository.count();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Author> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Author findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(format(AUTHOR_NOT_FOUND, id)));
    }

    public Author save(Author author) {
        validate(author);
        return repository.save(author);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ObjectNotFoundException(format(AUTHOR_NOT_FOUND, id), e);
        }
    }

    private void validate(Author author) {
        List<String> errors = new ArrayList<>();

        if (isEmpty(author.getName())) {
            errors.add("Author name is null!");
        }

        if (!isEmpty(errors)) {
            throw new RequestException(join("\n", errors));
        }
    }
}
