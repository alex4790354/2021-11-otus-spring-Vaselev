package ru.otus.spring.noSql.service;


import ru.otus.spring.noSql.domain.Author;
import ru.otus.spring.noSql.domain.Book;
import ru.otus.spring.noSql.domain.Note;

import java.util.List;

public interface BookService {
    void addBook(String title, String authorId, String genreId);

    void deleteBook(String bookId);

    void saveBook(Book book);

    Book getBookByTitle(String title);

    List<Note> getBookComments(String id);

    long countBooks();

    List<Note> getAllComments();

    boolean addBookComment(String bookId, String commentContent);

    void changeBookComment(String bookId, String commentContent);


    boolean deleteBookComment(String commentId);

    List<Book> getBooksByAuthor(Author author);

    List<Book> getAllBooks();

}
