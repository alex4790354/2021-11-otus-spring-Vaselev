package ru.otus.spring.orm.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.orm.domain.Book;
import ru.otus.spring.orm.repositories.BookRepository;
import ru.otus.spring.orm.services.BookService;


@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    static final String NULL_BOOK = "Book with this ID doesn't exist.";

    public Book getBookById(long id) {
        Book book = bookRepository.findBookById(id).orElse(null);
        if (book == null) throw new RuntimeException(NULL_BOOK);
        return book;
    }

}
