package ru.otus.spring.orm.repositories;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.orm.domain.Author;
import ru.otus.spring.orm.repositories.jpa.AuthorRepositoryJpa;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("ORM JPA Authors repository testing.")
@DataJpaTest
@Import(AuthorRepositoryJpa.class)
class AuthorRepositoryJPATest {

    private final static int EXPECTED_AUTHORS_COUNT = 4;
    private final static long AUTHOR_ONE_ID = 1L;
    private final static String AUTHOR_ONE_NAME = "Михаил Булгаков";
    private final static String AUTHOR_ONE_NAME_NEW = "Михаил Булгаков - New";


    @Autowired
    private AuthorRepository authorRepository;


    @DisplayName("Should get correct Author")
    @Test
    void shouldGetCorrectAuthor() {
        Optional<Author> author = authorRepository.getAuthorById(AUTHOR_ONE_ID);
        assertEquals(AUTHOR_ONE_ID, author.get().getId());
        assertEquals(AUTHOR_ONE_NAME, author.get().getName());
    }


    @DisplayName("Should find all Authors")
    @Test
    void ShouldGetAllAuthors() {
        val authors = authorRepository.getAllAuthors();
        assertThat(authors).isNotNull().hasSize(EXPECTED_AUTHORS_COUNT)
                .allMatch(s -> s.getId() > 0)
                .allMatch(s -> !s.getName().equals(""));
    }


    @DisplayName("Should be able to delete a Author:")
    @Test
    void shouldDeletefirstAuthor() {
        Author author = authorRepository.getAuthorById(AUTHOR_ONE_ID).get();
        assertEquals(AUTHOR_ONE_NAME, author.getName());
        // DELETE:
        authorRepository.delete(author);
        Optional<Author> authorOptional = authorRepository.getAuthorById(AUTHOR_ONE_ID);
        assertEquals(Optional.empty(), authorOptional);
    }


    @DisplayName("Should be able to insert a Author-1 after deletions")
    @Test
    void shouldAddNewAuthor() {
        Author author = new Author(0L, AUTHOR_ONE_NAME_NEW);
        Author savedAuthor = authorRepository.save(author);
        assertThat(savedAuthor.getId()).isGreaterThan(0);
        assertEquals(AUTHOR_ONE_NAME_NEW, savedAuthor.getName());
    }


}