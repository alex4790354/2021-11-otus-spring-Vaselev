package ru.otus.spring.jpa.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.spring.jpa.domain.Author;
import ru.otus.spring.jpa.domain.Book;
import ru.otus.spring.jpa.domain.Genre;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Books CRUD Repository test")
@DataJpaTest
//@Transactional (propagation = Propagation.NOT_SUPPORTED)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class BookJpaCrudTest {

    private final int EXPECTED_BOOKS_COUNT = 10;
    private final Author AUTHOR_ONE = new Author(1, "Михаил Булгаков");
    private final Author AUTHOR_NOT_EXIST = new Author(100, "Author not exist");
    private final Genre GENRE_ONE = new Genre(1, "Роман");
    private final String BOOK_ONE_NAME_OLD = "Мастер и Маргарита";
    private final String BOOK_ONE_NAME_UPDATED = "Мастер и Маргарита UPDATED";

    private final Book BOOK_ONE_OLD = new Book(1, AUTHOR_ONE, GENRE_ONE, BOOK_ONE_NAME_OLD);
    private final Book BOOK_ONE_UPDATED = new Book(1, AUTHOR_ONE, GENRE_ONE, BOOK_ONE_NAME_UPDATED);
    private final Book BOOK_NEW = new Book(AUTHOR_ONE, GENRE_ONE, BOOK_ONE_NAME_UPDATED);
    private final Book BOOK_CANT_BE_INSERTED = new Book(10, AUTHOR_NOT_EXIST, GENRE_ONE, BOOK_ONE_NAME_UPDATED);

    /*@Autowired
    private TestEntityManager em;*/
    @Autowired
    private BookRepository bookRepository;


    @Test
    public void bookCrudShouldReturnCorrectBook() {
        Optional<Book> book = bookRepository.findById(1L);
        assertEquals(BOOK_ONE_OLD, book.get());
    }

    @Test
    public void shouldWorkFindCount() {
        assertEquals(EXPECTED_BOOKS_COUNT, bookRepository.findCount());
    }

    @Test
    public void shouldWorkInsert() {
        assertEquals(EXPECTED_BOOKS_COUNT, bookRepository.findCount());
        Long newId = bookRepository.save(BOOK_NEW).getId();
        assertEquals(EXPECTED_BOOKS_COUNT + 1, bookRepository.findCount());
        assertEquals(BOOK_NEW, bookRepository.findById(newId).get());
        bookRepository.deleteById(newId);
    }


    @Test
    public void shouldDeleteRecord() {
        assertEquals(EXPECTED_BOOKS_COUNT, bookRepository.findCount());
        bookRepository.deleteById(1L);
        assertEquals(EXPECTED_BOOKS_COUNT -1, bookRepository.findCount());
        bookRepository.save(BOOK_ONE_OLD);
    }

    @Test
    public void shouldGetCorrectBookAfterUpdate() {
        bookRepository.updateBookNameById(1L, BOOK_ONE_NAME_UPDATED);
        assertEquals(BOOK_ONE_UPDATED, bookRepository.findById(1L).get());

        //  Why it doesn't update book.name second time ?
        /*bookRepository.updateBookNameById(1L, BOOK_ONE_NAME_OLD);
        assertEquals(BOOK_ONE_OLD, bookRepository.findById(1L).get());*/
    }

    @Test
    public void shouldGetCorrectBookAfterUpdate2() {
        bookRepository.updateBookNameById(1, BOOK_ONE_NAME_OLD);
        assertEquals(BOOK_ONE_OLD, bookRepository.findById(1L).get());
    }

}