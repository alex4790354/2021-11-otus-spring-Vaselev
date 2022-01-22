package ru.otus.spring.orm.services.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.orm.domain.Author;
import ru.otus.spring.orm.domain.Book;
import ru.otus.spring.orm.domain.Genre;
import ru.otus.spring.orm.domain.Note;
import ru.otus.spring.orm.repositories.jpa.BookRepositoryJpa;
import ru.otus.spring.orm.repositories.jpa.NoteRepositoryJpa;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("ORM JPA books repository testing.")
@DataJpaTest
@Import({BookServiceImpl.class, BookRepositoryJpa.class, NoteRepositoryJpa.class})
class BookServiceImplTest {

    private final static int EXPECTED_BOOKS_COUNT = 10;
    private final static Author AUTHOR_ONE = new Author(1, "Михаил Булгаков");
    private final static Author AUTHOR_NOT_EXIST = new Author(100, "Author not exist");
    private final static Genre GENRE_ONE = new Genre(1, "Роман");
    private final static String BOOK_ONE_NAME = "Мастер и Маргарита";
    private final static String BOOK_ONE_NAME_UPDATED = "Мастер и Маргарита - NEW";
    private final static Book BOOK_ONE = new Book(1, AUTHOR_ONE, GENRE_ONE, BOOK_ONE_NAME, new ArrayList<>());
    private final static List<Note> BOOK_ONE_RIEVIEWS = Arrays.asList(new Note(1, BOOK_ONE, "Note-01.1 - Мастер"), new Note(2, BOOK_ONE, "Note-01.2 - Мастер"));
    private final static Book BOOK_CANT_BE_INSERTED = new Book(10, AUTHOR_NOT_EXIST, GENRE_ONE, BOOK_ONE_NAME_UPDATED, new ArrayList<>());


    @Autowired  // TODO: change to Service
    private BookServiceImpl bookService;

    //TODO: fix test
    @DisplayName("Should get correct book")
    @Test
    void shouldGetCorrectBook() {
        Book book = bookService.getBookById(1L);
        assertThat(book).isNotNull()
                .matches(b -> b.getId() == BOOK_ONE.getId())
                .matches(b -> b.getTitle().equals(BOOK_ONE.getTitle()))
        //        .matches(b -> b.getGenre().equals(GENRE_ONE))
        //        .matches(b -> b.getAuthor().equals(AUTHOR_ONE))
        ;
                //.matches(b -> b.getNote().size() == BOOK_ONE_RIEVIEWS.size());
    }

    @DisplayName("Should find all books")
    @Test
    void ShouldFindAllBooks() {
        List<Book> books = bookService.getAllBooks();
        assertEquals(EXPECTED_BOOKS_COUNT, books.size());
    }

    @DisplayName("Should get correct books count")
    @Test
    void ShouldGetCorrectBooksCount() {
        assertEquals(EXPECTED_BOOKS_COUNT, bookService.getBooksCount());
    }

    @DisplayName("Should be able to delete a book:")
    @Test
    void shouldDeletefirstBook() {
        Book book = bookService.getBookById(1L);
        assertEquals(BOOK_ONE_NAME, book.getTitle());
        // DELETE:
        bookService.deleteBook(1L);
        assertThatCode(() -> bookService.getBookById(1L))
                .isInstanceOf(RuntimeException.class);
    }

    @DisplayName("Should be able to insert new book-1")
    @Test
    void shouldInsertNewBook() {
        Book book = new Book(new Author(1L, "Михаил Булгаков"), new Genre(1, "Роман"), "Мастер и Маргарита");
        Book savedBook = bookService.saveBook(book);
        assertThat(savedBook.getId()).isGreaterThan(0);
        assertEquals(book.getAuthor(), savedBook.getAuthor());
        assertEquals(book.getGenre(), savedBook.getGenre());
        assertEquals(book.getTitle(), savedBook.getTitle());
    }


    @DisplayName("Should trow an error")
    @Test
    void shouldTrowAnError() {
        assertThatCode(() -> bookService.saveBook(BOOK_CANT_BE_INSERTED))
                .isInstanceOf(PersistenceException.class);
    }


}