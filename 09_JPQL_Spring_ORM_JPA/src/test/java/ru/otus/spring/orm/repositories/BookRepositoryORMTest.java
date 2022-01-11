package ru.otus.spring.orm.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.orm.domain.Author;
import ru.otus.spring.orm.domain.Book;
import ru.otus.spring.orm.domain.Genre;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName(" JPA's books repository testing.")
@DataJpaTest
@Import(BookRepositoryORM.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryORMTest {


    private static final int EXPECTED_BOOKS_COUNT = 10;

    private final Author AUTHOR_ONE = new Author(1, "Михаил Булгаков");
    private final Author AUTHOR_UPDATED = new Author(2, "Антуан де Сент-Экзюпери");
    private final Author AUTHOR_NOT_EXIST = new Author(100, "Author not exist");
    private final Genre GENRE_ONE = new Genre(1, "Роман");
    private final Genre GENRE_UPDATED = new Genre(2, "Проза");
    private final String BOOK_ONE_NAME = "Мастер и Маргарита";
    private final String BOOK_ONE_NAME_UPDATED = "Мастер и Маргарита - NEW";

    private final Book BOOK_ONE = new Book(1, AUTHOR_ONE, GENRE_ONE, BOOK_ONE_NAME);
    private final Book BOOK_ONE_UPDATED = new Book(1, AUTHOR_UPDATED, GENRE_UPDATED, BOOK_ONE_NAME_UPDATED);
    private final Book BOOK_CANT_BE_INSERTED = new Book(10, AUTHOR_NOT_EXIST, GENRE_ONE, BOOK_ONE_NAME_UPDATED);

    private final int BOOKS_COUNT_START_WITH_O = 2;
    private final int BOOKS_COUNT_AUTHOR = 3;
    private final int BOOKS_COUNT_GENRE = 3;

    private final long BOOK_ID = 1l;
    private final long AUTHOR_ID = 1l;
    private final long GENRE_ID = 1l;
    private final List<String> BOOK_NAMES = Arrays.asList("Мастер и Маргарита", "Белая гвардия", "Собачье сердце", "Маленький принц", "Граф Монте-Кристо",
                                                "Трудно быть Богом", "Пикник на обочине", "Улитка на склоне", "Обитаемый остров", "Отель у погибшего альпениста");
    private static final List<String> BOOK_NAMES_START_WITH_M = Arrays.asList("Мастер и Маргарита", "Маленький принц");


    @Autowired
    private BookRepositoryORM bookRepository;


    @DisplayName("Should find all books")
    @Test
    void ShouldFindAllBooks() {
        List<Book> books = bookRepository.findAll();
        assertEquals(EXPECTED_BOOKS_COUNT, books.size());
    }


    @DisplayName("Should get correct books count")
    @Test
    void findGenreCount() {
        Long genreCount = bookRepository.getBooksCount();
        assertEquals(EXPECTED_BOOKS_COUNT, genreCount);
    }


    @DisplayName("Should get correct book")
    @Test
    void shouldGetCorrectBook() {
        Optional<Book> book = bookRepository.findBookById(BOOK_ID);
        assertEquals(BOOK_ONE, book.get());
    }


    @DisplayName("Should get correct all book Names")
    @Test
    void shouldGetAllCorrectBookNames() {
        List<String> bookNames = bookRepository.findAllBookNames();
        assertLinesMatch(BOOK_NAMES, bookNames);
    }


    @DisplayName("Should get correct 3 books by author")
    @Test
    void shouldGetAllBooksByAuthor() {
        List<Book> bookNames = bookRepository.findBooksByAuthorName("Михаил Булгаков");
        assertEquals(BOOKS_COUNT_AUTHOR, bookNames.size());
    }


    @DisplayName("Should get correct 3 books by author")
    @Test
    void shouldGetAllBooksLikeName() {
        List<Book> bookNames = bookRepository.findBooksByName("Ма%");
        assertEquals(BOOKS_COUNT_START_WITH_O, bookNames.size());
    }


    @DisplayName("Should get correct 3 books by author")
    @Test
    void shouldGetAllBooksLikeGenre() {
        List<Book> bookNames = bookRepository.findBooksByGenreName("П%");
        assertEquals(BOOKS_COUNT_GENRE, bookNames.size());
    }


    @DisplayName("Should update book name")
    @Test
    void shouldUpdateBookName() {
        Book oldBook = bookRepository.findBookById(1L).get();
        assertEquals(BOOK_ONE_NAME, oldBook.getName());
        bookRepository.updateBookName(BOOK_ONE_NAME, BOOK_ONE_NAME_UPDATED);
        Book newBook = bookRepository.findBookById(1L).get();
        assertEquals(BOOK_ONE_NAME, newBook.getName());
        bookRepository.updateBookName(BOOK_ONE_NAME_UPDATED, BOOK_ONE_NAME);
    }


    @DisplayName("Delete 1 book")
    @Test
    void shouldDeletefirstBook() {
        Book book = bookRepository.findBookById(1L).get();
        assertEquals(BOOK_ONE_NAME, book.getName());
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