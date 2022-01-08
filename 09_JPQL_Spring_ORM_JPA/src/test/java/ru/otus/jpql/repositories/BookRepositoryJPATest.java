package ru.otus.jpql.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.jpql.domain.Author;
import ru.otus.jpql.domain.Book;
import ru.otus.jpql.domain.Genre;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName(" JPA's books repository testing.")
@DataJpaTest
@Import(BookRepositoryJPA.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryJPATest {

    private static final int BOOKS_COUNT = 10;
    private static final int BOOKS_COUNT_START_WITH_O = 2;
    private static final int BOOKS_COUNT_AUTHOR = 3;
    private static final int BOOKS_COUNT_GENRE = 3;
    private static final String BOOK_NAME_OLD = "Мастер и Маргарита";
    private static final String BOOK_NAME_NEW = "Мастер и Маргарита - NEW";
    private static final long BOOK_ID = 1l;
    private static final long AUTHOR_ID = 1l;
    private static final long GENRE_ID = 1l;
    private static final List<String> BOOK_NAMES = Arrays.asList("Мастер и Маргарита", "Белая гвардия", "Собачье сердце", "Маленький принц", "Граф Монте-Кристо",
                                                "Трудно быть Богом", "Пикник на обочине", "Улитка на склоне", "Обитаемый остров", "Отель у погибшего альпениста");
    private static final List<String> BOOK_NAMES_M = Arrays.asList("Мастер и Маргарита", "Маленький принц");


    @Autowired
    private BookRepositoryJPA bookRepository;

    @DisplayName("Should find all books")
    @Test
    void ShouldFindAllBooks() {
        List<Book> books = bookRepository.findAll();
        assertEquals(BOOKS_COUNT, books.size());
    }

    @DisplayName("Should get correct books count")
    @Test
    void findGenreCount() {
        Long genreCount = bookRepository.getBooksCount();
        assertEquals(BOOKS_COUNT, genreCount);
    }

    @DisplayName("Should get correct book")
    @Test
    void shouldGetCorrectBook() {
        Optional<Book> book = bookRepository.findBookById(BOOK_ID);
        assertEquals(BOOK_NAME_OLD, book.get().getName());
        assertEquals(BOOK_ID, book.get().getId());
        assertEquals(AUTHOR_ID, book.get().getAuthor().getId());
        assertEquals(GENRE_ID, book.get().getGenre().getId());
    }

    @DisplayName("Should get correct all books")
    @Test
    void shouldGetAllCorrectBookNames() {
        List<String> bookNames = bookRepository.findAllBookNames();
        assertLinesMatch(BOOK_NAMES, bookNames);
    }

    @DisplayName("Should get correct 3 books by author")
    @Test
    void shouldGetAllBooksByAuthor() {
        List<Book> bookNames = bookRepository.findBookByAuthorName("Михаил Булгаков");
        assertEquals(BOOKS_COUNT_AUTHOR, bookNames.size());
    }

    @DisplayName("Should get correct 3 books by author")
    @Test
    void shouldGetAllBooksLikeGenre() {
        List<Book> bookNames = bookRepository.findBookByGenreName("П%");
        assertEquals(BOOKS_COUNT_GENRE, bookNames.size());
    }

    @DisplayName("Should get correct 3 books by author")
    @Test
    void shouldGetAllBooksLikeName() {
        List<Book> bookNames = bookRepository.findBookByName("Ма%");
        assertEquals(BOOKS_COUNT_START_WITH_O, bookNames.size());
    }

    @DisplayName("Should update book name")
    @Test
    void shouldUpdateBookName() {
        Book oldBook = bookRepository.findBookById(1L).get();
        assertEquals(BOOK_NAME_OLD, oldBook.getName());
        bookRepository.updateBookName(BOOK_NAME_OLD, BOOK_NAME_NEW);
        Book newBook = bookRepository.findBookById(1L).get();
        assertEquals(BOOK_NAME_OLD, newBook.getName());
        bookRepository.updateBookName(BOOK_NAME_NEW, BOOK_NAME_OLD);
    }

    @DisplayName("Delete 1 book")
    @Test
    void shouldDeletefirstBook() {
        Book book = bookRepository.findBookById(1L).get();
        assertEquals(BOOK_NAME_OLD, book.getName());
        bookRepository.deleteById(1L);
        Optional<Book> bookOptional = bookRepository.findBookById(1L);
        assertEquals(Optional.empty(), bookOptional);
    }

    @DisplayName("Delete 1 book")
    @Test
    void shouldInsertNewBook() {
        Book book = new Book(1L, new Author(1L, "Михаил Булгаков"), new Genre(1, "Роман"), "Мастер и Маргарита");
        bookRepository.insertNewBook(book);
        Optional<Book> newBook = bookRepository.findBookById(1L);
        assertEquals(book.getAuthor(), newBook.get().getAuthor());
        assertEquals(book.getGenre(), newBook.get().getGenre());
        assertEquals(book.getName(), newBook.get().getName());
    }

}