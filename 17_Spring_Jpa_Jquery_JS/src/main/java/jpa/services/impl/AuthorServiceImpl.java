package jpa.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.jpa.customExceptions.DaoException;
import ru.otus.spring.jpa.domain.Author;
import ru.otus.spring.jpa.repositories.AuthorRepository;
import ru.otus.spring.jpa.services.AuthorService;

import java.util.List;


@Component
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private static final String AUTHOR_NOT_EXIST = "no author found by id";

    @Transactional(readOnly = true)
    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }


    @Transactional(readOnly = true)
    @Override
    public Author findById(long id) {
        return authorRepository.findById(id).orElseThrow(() -> new DaoException(AUTHOR_NOT_EXIST));
    }

    @Transactional
    @Override
    public Author create(String fullName) {
        Author author = new Author(0L, fullName);
        return authorRepository.save(author);
    }

    
    @Transactional
    @Override
    public void save(long id, String fullName) {
        Author author = authorRepository.findById(id).orElse(null);
        if (author == null) {
            throw new DaoException(AUTHOR_NOT_EXIST);
        }
        author.setName(fullName);
        authorRepository.save(author);
    }


    
    @Transactional
    @Override
    public void delete(long authorId) {
        Author author = authorRepository.findById(authorId).orElse(null);
        if (author != null) {
            authorRepository.delete(author);
        }

    }
}
