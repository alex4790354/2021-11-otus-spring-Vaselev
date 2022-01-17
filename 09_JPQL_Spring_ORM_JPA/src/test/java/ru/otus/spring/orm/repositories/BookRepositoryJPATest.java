package ru.otus.spring.orm.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.orm.domain.Author;
import ru.otus.spring.orm.domain.Book;
import ru.otus.spring.orm.domain.Review;
import ru.otus.spring.orm.domain.Genre;
import ru.otus.spring.orm.repositories.jpa.BookRepositoryJPA;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("ORM JPA books repository testing.")
@DataJpaTest
@Import(BookRepositoryJPA.class)
class BookRepositoryJPATest {

    private final static int EXPECTED_BOOKS_COUNT = 10;
    private final static int BOOKS_COUNT_START_WITH_O = 2;
    private final static Author AUTHOR_ONE = new Author(1, "Михаил Булгаков");
    private final static Author AUTHOR_NOT_EXIST = new Author(100, "Author not exist");
    private final static Genre GENRE_ONE = new Genre(1, "Роман");
    private final static String BOOK_ONE_NAME = "Мастер и Маргарита";
    private final static String BOOK_ONE_NAME_UPDATED = "Мастер и Маргарита - NEW";
    private final static Book BOOK_ONE = new Book(1, AUTHOR_ONE, GENRE_ONE, BOOK_ONE_NAME, new ArrayList<>());
    private final static List<Review> BOOK_ONE_RIEVIEWS = Arrays.asList(new Review(1, BOOK_ONE, "Note-01.1 - Мастер"), new Review(2, BOOK_ONE, "Note-01.2 - Мастер"));
    private final static Book BOOK_CANT_BE_INSERTED = new Book(10, AUTHOR_NOT_EXIST, GENRE_ONE, BOOK_ONE_NAME_UPDATED, new ArrayList<>());
    private final static long BOOK_ID = 1l;

    @Autowired
    private BookRepositoryJPA bookRepository;

    @DisplayName("Should get correct book")
    @Test
    void shouldGetCorrectBook() {
        Optional<Book> book = bookRepository.getBookById(BOOK_ID);
        BOOK_ONE.setReviews(BOOK_ONE_RIEVIEWS);
        assertEquals(BOOK_ONE.getId(), book.get().getId());
        assertEquals(BOOK_ONE.getAuthor(), book.get().getAuthor());
        assertEquals(BOOK_ONE.getGenre(), book.get().getGenre());
        assertEquals(BOOK_ONE.getReviews().size(), book.get().getReviews().size());
    }

    @DisplayName("Should find all books")
    @Test
    void ShouldGetAllBooks() {
        List<Book> books = bookRepository.getAllBooks();
        assertEquals(EXPECTED_BOOKS_COUNT, books.size());
    }


    @DisplayName("Should get correct books count")
    @Test
    void findGenreCount() {
        Long genreCount = bookRepository.getBooksCount();
        assertEquals(EXPECTED_BOOKS_COUNT, genreCount);
    }


    @DisplayName("Should be able to delete a book:")
    @Test
    void shouldDeletefirstBook() {
        Book book = bookRepository.getBookById(1L).get();
        assertEquals(BOOK_ONE_NAME, book.getTitle());
        // DELETE:
        bookRepository.deleteBook(book);
        Optional<Book> bookOptional = bookRepository.getBookById(1L);
        assertEquals(Optional.empty(), bookOptional);
    }


    @DisplayName("Should be able to insert a book-1 after deletions")
    @Test
    void shouldInsertNewBook() {
        Book book = new Book( new Author(1L, "Михаил Булгаков"), new Genre(1, "Роман"), "Мастер и Маргарита");
        Book savedBook = bookRepository.saveBook(book);
        assertThat(savedBook.getId()).isGreaterThan(0);
        assertEquals(book.getAuthor(), savedBook.getAuthor());
        assertEquals(book.getGenre(), savedBook.getGenre());
        assertEquals(book.getTitle(), savedBook.getTitle());
    }


    @DisplayName("Should trow an error")
    @Test
    void shouldTrowAnError() {
        assertThatCode(() -> bookRepository.saveBook(BOOK_CANT_BE_INSERTED))
                .isInstanceOf(PersistenceException.class);
    }

}