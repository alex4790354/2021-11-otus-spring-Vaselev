package ru.otus.spring.jdbc.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring.jdbc.customExceptions.DaoException;
import ru.otus.spring.jdbc.dao.interfaces.BookDao;
import ru.otus.spring.jdbc.domain.Author;
import ru.otus.spring.jdbc.domain.Book;
import ru.otus.spring.jdbc.domain.Genre;


@DisplayName("BooksDao for books manipulation test")
@ExtendWith(SpringExtension.class)
@JdbcTest
@Import(BookDaoJdbc.class)
@ComponentScan({"ru.otus.spring.jdbc"})
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class BookDaoJdbcTest {

    private final int EXPECTED_BOOKS_COUNT = 10;

    private final Author AUTHOR_ONE = new Author(1, "Михаил Булгаков");
    private final Author AUTHOR_NOT_EXIST = new Author(100, "Author not exist");
    private final Genre GENRE_ONE = new Genre(1, "Роман");
    private final String BOOK_ONE_NAME = "Мастер и Маргарита";
    private final String BOOK_ONE_NAME_UPDATED = "Мастер и Маргарита UPDATED";

    private final Book BOOK_ONE = new Book(1, AUTHOR_ONE, GENRE_ONE, BOOK_ONE_NAME);
    private final Book BOOK_ONE_UPDATED = new Book(1, AUTHOR_ONE, GENRE_ONE, BOOK_ONE_NAME_UPDATED);
    private final Book BOOK_CANT_BE_INSERTED = new Book(10, AUTHOR_NOT_EXIST, GENRE_ONE, BOOK_ONE_NAME_UPDATED);


    @Autowired
    private BookDao bookDao;


    @DisplayName("Should return expected Books count")
    @Test
    void shouldReturnExpectedBooksCount() {
        int actualBooksCount = bookDao.getCount();
        assertEquals(EXPECTED_BOOKS_COUNT, actualBooksCount);
    }


    @Test
    void getByIdBookShouldReturnExpectedBook() {
        assertEquals(BOOK_ONE, bookDao.getById(1));
    }

    @Test
    void shouldUpdateBookById() {
        bookDao.updateById( 1, BOOK_ONE_NAME_UPDATED);
        assertEquals(BOOK_ONE_UPDATED, bookDao.getById(1));
    }

   @Test
    void booksCountShouldDecressAfterDeleteById() throws DaoException {
        assertEquals(EXPECTED_BOOKS_COUNT, bookDao.getCount());
        bookDao.deleteById(1);
        assertEquals(EXPECTED_BOOKS_COUNT - 1, bookDao.getCount());
        bookDao.insert(BOOK_ONE);
       assertEquals(EXPECTED_BOOKS_COUNT, bookDao.getCount());
    }

    @Test
    void booksCountShouldincreasedAfterInsert() throws DaoException {
        bookDao.insert(BOOK_ONE_UPDATED);
        assertEquals(EXPECTED_BOOKS_COUNT + 1, bookDao.getCount());
    }

    @DisplayName("Should trow an error")
    @Test
    void shouldTrowAnError() {
        assertThatCode(() -> bookDao.insert(BOOK_CANT_BE_INSERTED))
                .isInstanceOf(DataIntegrityViolationException.class);
    }



}