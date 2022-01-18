package ru.otus.spring.jpa.services;

import ru.otus.spring.jpa.domain.Book;
import ru.otus.spring.jpa.domain.Review;

import java.util.List;

public interface BookService {

    Book getBookById(long id);

    List<Book> getAllBooks();

    Long getBooksCount();

    void deleteBook(long id);

    Book saveBook(Book book);

    Book saveBook(Long id, String newTitle);

    //List<Review> getReviewsByBookId(Long bookId);

}
