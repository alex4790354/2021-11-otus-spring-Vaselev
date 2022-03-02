package ru.otus.spring.mvc.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.mvc.customExceptions.DaoException;
import ru.otus.spring.mvc.domain.Book;
import ru.otus.spring.mvc.repositories.BookRepository;
import ru.otus.spring.mvc.services.BookService;
import java.util.List;


@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    static final String BOOK_NOT_EXIST = "Book with this ID doesn't exist.";


    @Transactional(readOnly = true)
    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }


    @Transactional(readOnly = true)
    @Override
    public Book findById(long id) {
        return bookRepository.findById(id).orElseThrow(() -> new DaoException(BOOK_NOT_EXIST));
    }


    @Transactional(readOnly = true)
    @Override
    public Long getBooksCount() {
        return bookRepository.count();
    }

    
    @Transactional
    @Override
    public void deleteBook(long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            bookRepository.delete(book);
        }
    }


    @Transactional
    @Override
    public Book saveBook(Book book) {
        if (book != null) {
            return bookRepository.save(book);
        } else {
            throw new DaoException(BOOK_NOT_EXIST);
        }
    }

    @Transactional
    @Override
    public Book saveBook(Long id, String newTitle) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) {
            throw new DaoException(BOOK_NOT_EXIST);
        }
        book.setTitle(newTitle);
        return saveBook(book);
    }


}
