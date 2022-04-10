package ru.otus.spring.actuator.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.actuator.domain.Author;
import ru.otus.spring.actuator.exceptions.OtusException;
import ru.otus.spring.actuator.exceptions.RequestException;
import ru.otus.spring.actuator.exceptions.ObjectNotFoundException;
import ru.otus.spring.actuator.repository.AuthorRepository;
import ru.otus.spring.actuator.services.AuthorService;
import java.util.ArrayList;
import java.util.List;
import static java.lang.String.format;
import static java.lang.String.join;
import static org.springframework.util.ObjectUtils.isEmpty;


@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    public static final String AUTHOR_NOT_FOUND = "Author not found. Id = %s";

    private final AuthorRepository authorRepository;

    @Transactional(readOnly = true)
    @Override
    public Long count() {
        return authorRepository.count();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(format(AUTHOR_NOT_FOUND, id)));
    }

    @Transactional
    @Override
    public Author save(Author author) {
        validate(author);
        return authorRepository.save(author);
    }

    @Transactional
    @Override
    public Author save(String fullName) {
        Author author = new Author(0L, fullName);
        return authorRepository.save(author);
    }

    @Transactional
    @Override
    public void save(long id, String fullName) {
        Author author = authorRepository.findById(id).orElse(null);
        if (author == null) {
            throw new OtusException(AUTHOR_NOT_FOUND);
        }
        author.setName(fullName);
        authorRepository.save(author);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        try {
            authorRepository.deleteById(id);
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
