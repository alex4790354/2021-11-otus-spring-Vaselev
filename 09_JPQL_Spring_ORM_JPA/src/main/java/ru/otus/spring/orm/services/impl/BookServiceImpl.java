package ru.otus.spring.orm.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.orm.customExceptions.DaoException;
import ru.otus.spring.orm.domain.Book;
import ru.otus.spring.orm.domain.Note;
import ru.otus.spring.orm.repositories.BookRepository;
import ru.otus.spring.orm.repositories.NoteRepository;
import ru.otus.spring.orm.services.BookService;
import java.util.List;


@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final NoteRepository noteRepository;
    static final String BOOK_NOT_EXIST = "Book with this ID doesn't exist.";


    @Transactional(readOnly = true)
    @SneakyThrows
    @Override
    public Book getBookById(long id) {
        Book book = bookRepository.getBookById(id).orElse(null);
        if (book == null) throw new DaoException(BOOK_NOT_EXIST, new RuntimeException());
        return book;
    }


    @Transactional(readOnly = true)
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }


    @Transactional(readOnly = true)
    @Override
    public List<Note> getNoteByBookId(Long bookId) {
        return noteRepository.getNoteByBookId(bookId);
    }


    @Transactional(readOnly = true)
    @Override
    public Long getBooksCount() {
        return bookRepository.getBooksCount();
    }

    @SneakyThrows
    @Transactional
    @Override
    public void deleteBook(long id) {
        Book book = bookRepository.getBookById(id).orElse(null);
        if (book != null) {
            bookRepository.deleteBook(book);
        }
    }


    @Transactional
    @SneakyThrows
    @Override
    public Book saveBook(Book book) {
        if (book != null) {
            return bookRepository.saveBook(book);
        } else {
            throw new DaoException(BOOK_NOT_EXIST, new RuntimeException());
        }
    }

    @Transactional
    @SneakyThrows
    @Override
    public Book saveBook(Long id, String newTitle) {
        Book book = bookRepository.getBookById(id).orElse(null);
        if (book == null) {
            throw new DaoException(BOOK_NOT_EXIST, new RuntimeException());
        }
        book.setTitle(newTitle);
        return saveBook(book);
    }


}
