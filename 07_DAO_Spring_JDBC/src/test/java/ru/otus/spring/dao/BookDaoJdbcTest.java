package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring.dao.interfaces.BookDao;

@DisplayName("BooksDao for books manipulation test")
@ExtendWith(SpringExtension.class)
@JdbcTest
@Import(BookDaoJdbc.class)

//@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class BookDaoJdbcTest {

    private static final int EXPECTED_BOOKS_COUNT = 9;

    @Autowired
    private BookDao bookDao;

    /**
     *
     * @JdbcTest не работают для тестирования классов, в которых использовался NamedParameterJdbcOperations
     * (хотя работало нормально на Jdbc)
     * Объяснений, примера или документации того как делать с npJdbc пока не нашел.
     *
     * */


    /*
    @DisplayName("Should return expected Books count")
    @Test
    void shouldReturnExpectedBooksCount() {

        int actualBooksCount = bookDao.getCount();
        assertEquals(actualBooksCount, EXPECTED_BOOKS_COUNT);
    }

    @Test
    void getByIdBookShouldReturnExpectedBook() {
        Author author = new Author(1, "Михаил Булгаков");
        Genre genre = new Genre(1, "Роман");
        Book expectedBook = new Book(1, author, genre, "Мастер и Маргарита");
        assertEquals(bookDao.getById(1), expectedBook);
    }

    @Test
    void updateById() {
        Author author = new Author(1, "Михаил Булгаков");
        Genre genre = new Genre(1, "Роман");
        bookDao.updateById( 1, "Мастер и Маргарита UPDATED");

        Book expectedBook = new Book(1, author, genre, "Мастер и Маргарита UPDATED");
        assertEquals(bookDao.getById(1), expectedBook);
    }

   @Test
    void booksCountShouldDecressAfterDeleteById() {
        assertEquals(bookDao.getCount(), EXPECTED_BOOKS_COUNT);
        bookDao.deleteById(2);
        assertEquals(bookDao.getCount(), EXPECTED_BOOKS_COUNT - 1);
    }

    @Test
    void booksCountShouldincreasedAfterCorrectInsert() throws WrongSqlStatement {
        int count = bookDao.getCount();
        bookDao.insert("Аркадий и Борис Стругацкие", "Фантастика", "Отель у погибшего альпениста 1");
        assertEquals(bookDao.getCount(), count + 1);
    }

    @Test
    void incorrectInsertShouldGenerateException() {

        Throwable thrown = assertThrows(WrongSqlStatement.class, () -> {
            bookDao.insert("Нет такого автора", "Нет такого жанра", "Отель у погибшего альпениста 2");
        });
        assertEquals(thrown.getMessage(), "Error: Author or genre doesn't exist. Please check and correct it");
    }*/

    /*@Test
        void getAll() {
        too much for not
    }*/

}