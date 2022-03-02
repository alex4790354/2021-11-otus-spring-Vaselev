package ru.otus.spring.mvc.repositories.impl;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.spring.mvc.domain.Author;
import ru.otus.spring.mvc.domain.Book;
import ru.otus.spring.mvc.domain.Genre;
import ru.otus.spring.mvc.repositories.BookRepository;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("JPA books repository testing.")
@DataJpaTest
class BookRepositoryTest {

    private final static int EXPECTED_BOOKS_COUNT = 10;
    private final static Author AUTHOR_ONE = new Author(1, "Михаил Булгаков");
    private final static Genre GENRE_ONE = new Genre(1, "Роман");
    private final static String BOOK_ONE_NAME = "Мастер и Маргарита";
    private final static Book BOOK_ONE = new Book(1, AUTHOR_ONE, GENRE_ONE, BOOK_ONE_NAME);
    private final static long BOOK_ID = 1l;

    @Autowired
    private BookRepository bookRepository;


    @DisplayName("Should find all books")
    @Test
    void ShouldGetAllBooks() {
        List<Book> books = bookRepository.findAll();
        org.assertj.core.api.Assertions.assertThat(books).isNotNull().hasSize(EXPECTED_BOOKS_COUNT)
                .allMatch(s -> s.getId() > 0)
                .allMatch(s -> s.getAuthor().getId() > 0)
                .allMatch(s -> !s.getAuthor().getName().equals(""))
                .allMatch(s -> s.getGenre().getId() > 0)
                .allMatch(s -> !s.getGenre().getName().equals(""))
                .allMatch(s -> !s.getTitle().equals(""));
    }


    @DisplayName("Should get correct book")
    @Test
    void shouldGetCorrectBook() {
        Book book = bookRepository.getById(BOOK_ID);
        assertEquals(BOOK_ONE.getId(), book.getId());
    }


}