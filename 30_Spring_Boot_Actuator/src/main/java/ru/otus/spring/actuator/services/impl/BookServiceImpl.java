package ru.otus.spring.actuator.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.actuator.domain.Book;
import ru.otus.spring.actuator.exceptions.RequestException;
import ru.otus.spring.actuator.exceptions.ObjectNotFoundException;
import ru.otus.spring.actuator.repository.BookRepository;
import ru.otus.spring.actuator.services.BookService;

import java.util.List;

import static java.lang.String.format;
import static org.springframework.util.ObjectUtils.isEmpty;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    public static final String BOOK_NOT_FOUND = "Book not found. Id: %s";
    private final BookRepository bookRepository;

    @Transactional(readOnly = true)
    @Override
    public Long count() {
        return bookRepository.count();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(format(BOOK_NOT_FOUND, id)));
    }

    @Transactional
    @Override
    public Book save(Book book) {
        validate(book);
        return bookRepository.save(book);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        try {
            bookRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ObjectNotFoundException(format(BOOK_NOT_FOUND, id), e);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> findByAuthorId(Long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> findByGenreId(Long genreId) {
        return bookRepository.findByGenreId(genreId);
    }

    private void validate(Book book) {
        if (isEmpty(book.getTitle())) {
            throw new RequestException("Book name is null or empty!");
        }

        if (book.getAuthor() == null) {
            throw new RequestException("Book author is null or empty!");
        }

        if (book.getGenre() == null) {
            throw new RequestException("Book genre is null or empty!");
        }
    }
}
