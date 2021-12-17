package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring.customExceptions.WrongSqlStatement;
import ru.otus.spring.domain.Book;


import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BooksDao for books manipulation test")
@ExtendWith(SpringExtension.class)
@JdbcTest
@Import(BookDaoJdbc.class)
// Где смотреть как можно подменять БД и data.sql и какие при этом должны быть настройки @AutoConfigureTestDatabase ?
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class BookDaoJdbcTest {

    private static final int EXPECTED_BOOKS_COUNT = 9;

    @Autowired
    private BookDaoJdbc bookDaoJdbc;

    @DisplayName("Should return expected Books count")
    @Test
    void shouldReturnExpectedBooksCount() {

        int actualBooksCount = bookDaoJdbc.getCount();
        assertEquals(actualBooksCount, EXPECTED_BOOKS_COUNT);
    }

    @Test
    void getByIdBookShouldReturnExpectedBook() {
        Book expectedBook = new Book(1, 1, 1, "Мастер и Маргарита");
        assertEquals(bookDaoJdbc.getById(1), expectedBook);
    }

    @Test
    void updateById() {
        bookDaoJdbc.updateById( 1, "Мастер и Маргарита UPDATED");
        Book expectedBook = new Book(1, 1, 1, "Мастер и Маргарита UPDATED");
        assertEquals(bookDaoJdbc.getById(1), expectedBook);
    }

   @Test
    void booksCountShouldDecressAfterDeleteById() {
        assertEquals(bookDaoJdbc.getCount(), EXPECTED_BOOKS_COUNT);
        bookDaoJdbc.deleteById(2);
        assertEquals(bookDaoJdbc.getCount(), EXPECTED_BOOKS_COUNT - 1);
    }

    @Test
    void booksCountShouldincreasedAfterCorrectInsert() throws WrongSqlStatement {
        int count = bookDaoJdbc.getCount();
        bookDaoJdbc.insert("Аркадий и Борис Стругацкие", "Фантастика", "Отель у погибшего альпениста 1");
        assertEquals(bookDaoJdbc.getCount(), count + 1);
    }

    @Test
    void incorrectInsertShouldGenerateException() {

        Throwable thrown = assertThrows(WrongSqlStatement.class, () -> {
            bookDaoJdbc.insert("Нет такого автора", "Нет такого жанра", "Отель у погибшего альпениста 2");
        });
        assertEquals(thrown.getMessage(), "Error: Author or genre doesn't exist. Please check and correct it");
    }

    /*@Test
        void getAll() {
        too much for not
    }*/

}