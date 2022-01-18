package ru.otus.spring.jpa.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.jpa.customExceptions.DaoException;
import ru.otus.spring.jpa.domain.Book;
import ru.otus.spring.jpa.domain.Review;
import ru.otus.spring.jpa.repositories.BookRepository;
import ru.otus.spring.jpa.repositories.ReviewRepository;
import ru.otus.spring.jpa.repositories.ReviewRepositoryCustom;
import ru.otus.spring.jpa.services.BookService;

import java.util.List;


@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewRepositoryCustom reviewRepositoryCustom;
    static final String BOOK_NOT_EXIST = "Book with this ID doesn't exist.";


    @Transactional(readOnly = true)
    @SneakyThrows
    @Override
    public Book getBookById(long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) throw new DaoException(BOOK_NOT_EXIST, new RuntimeException());
        return book;
    }


    @Transactional(readOnly = true)
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }


    /*@Transactional(readOnly = true)
    @Override
    public List<Review> getReviewsByBookId(Long bookId) {
        return reviewRepositoryCustom.getReviewsByBookId(bookId);
    }*/


    @Transactional(readOnly = true)
    @Override
    public Long getBooksCount() {
        return bookRepository.count();
    }

    @SneakyThrows
    @Transactional
    @Override
    public void deleteBook(long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) {
            throw new DaoException(BOOK_NOT_EXIST, new RuntimeException());
        }
        bookRepository.delete(book);
    }


    @Transactional
    @SneakyThrows
    @Override
    public Book saveBook(Book book) {
        if (book != null) {
            return bookRepository.save(book);
        } else {
            throw new DaoException(BOOK_NOT_EXIST, new RuntimeException());
        }
    }

    @Transactional
    @SneakyThrows
    @Override
    public Book saveBook(Long id, String newTitle) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) {
            throw new DaoException(BOOK_NOT_EXIST, new RuntimeException());
        }
        book.setTitle(newTitle);
        return saveBook(book);
    }


}
