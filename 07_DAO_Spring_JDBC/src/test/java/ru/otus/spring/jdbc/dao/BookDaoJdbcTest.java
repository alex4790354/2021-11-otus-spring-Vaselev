package ru.otus.spring.jdbc.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import ru.otus.spring.jdbc.dao.interfaces.BookDao;
import ru.otus.spring.jdbc.domain.Author;
import ru.otus.spring.jdbc.domain.Book;
import ru.otus.spring.jdbc.domain.Genre;


@DisplayName("BooksDao for books manipulation test")
@JdbcTest
@Import(BookDaoJdbc.class)
class BookDaoJdbcTest {

    private final int EXPECTED_BOOKS_COUNT = 10;

    private final Author AUTHOR_ONE = new Author(1, "Михаил Булгаков");
    private final Author AUTHOR_UPDATED = new Author(2, "Антуан де Сент-Экзюпери");
    private final Author AUTHOR_NOT_EXIST = new Author(100, "Author not exist");
    private final Genre GENRE_ONE = new Genre(1, "Роман");
    private final Genre GENRE_UPDATED = new Genre(2, "Проза");
    private final String BOOK_ONE_NAME = "Мастер и Маргарита";
    private final String BOOK_ONE_NAME_UPDATED = "Мастер и Маргарита UPDATED";

    private final Book BOOK_ONE = new Book(1, AUTHOR_ONE, GENRE_ONE, BOOK_ONE_NAME);
    private final Book BOOK_ONE_UPDATED = new Book(1, AUTHOR_UPDATED, GENRE_UPDATED, BOOK_ONE_NAME_UPDATED);
    private final Book BOOK_CANT_BE_INSERTED = new Book(10, AUTHOR_NOT_EXIST, GENRE_ONE, BOOK_ONE_NAME_UPDATED);


    @Autowired
    private BookDao bookDao;


    @DisplayName("Should return expected Books count")
    @Test
    void shouldReturnExpectedBooksCount() {
        int actualBooksCount = bookDao.getCount();
        assertEquals(EXPECTED_BOOKS_COUNT, actualBooksCount);
    }


    @DisplayName("Should return expected book by bookId")
    @Test
    void getByIdBookShouldReturnExpectedBook() {
        assertEquals(BOOK_ONE, bookDao.getById(1));
    }


    @DisplayName("Should correctly update book")
    @Test
    void shouldUpdateBookByBookId() {
        bookDao.updateById(BOOK_ONE_UPDATED);
        assertEquals(BOOK_ONE_UPDATED, bookDao.getById(1));
        bookDao.updateById(BOOK_ONE);
        assertEquals(BOOK_ONE, bookDao.getById(1));
    }


    @DisplayName("Should correctly update book.name ")
    @Test
    void shouldUpdateBookNameByBookId() {
        bookDao.updateNameById( 1, BOOK_ONE_NAME_UPDATED);
        assertEquals(BOOK_ONE_NAME_UPDATED, bookDao.getById(1).getName());
        bookDao.updateNameById( 1, BOOK_ONE_NAME);
        assertEquals(BOOK_ONE_NAME, bookDao.getById(1).getName());
    }


   @DisplayName("Should correctly delete and insert book")
   @Test
    void booksCountShouldDecressAfterDeleteById() {
        assertEquals(EXPECTED_BOOKS_COUNT, bookDao.getCount());
        bookDao.deleteById(1);
        assertEquals(EXPECTED_BOOKS_COUNT - 1, bookDao.getCount());
        bookDao.insert(BOOK_ONE);
        assertEquals(EXPECTED_BOOKS_COUNT, bookDao.getCount());
    }


    @DisplayName("Should trow an error")
    @Test
    void shouldTrowAnError() {
        assertThatCode(() -> bookDao.insert(BOOK_CANT_BE_INSERTED))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

}