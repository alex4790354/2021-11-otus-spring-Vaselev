package ru.otus.spring.mvc.services.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.mvc.domain.Author;
import ru.otus.spring.mvc.services.AuthorService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("ORM JPA Authors repository testing.")
@DataJpaTest
@Import(AuthorServiceImpl.class)
class AuthorServiceImplTest {

    private final static int EXPECTED_AUTHORS_COUNT = 4;
    private final static long AUTHOR_ONE_ID = 1L;
    private final static String AUTHOR_ONE_NAME = "Михаил Булгаков-test";
    private final static String AUTHOR_ONE_NAME_NEW = "Михаил Булгаков - New";
    private final static Author AUTHOR_ONE_UPDATED = new Author(AUTHOR_ONE_ID, AUTHOR_ONE_NAME_NEW);

    @Autowired
    private AuthorService authorService;

    @Autowired
    private TestEntityManager em;


    @DisplayName("Should get correct Author")
    @Test
    void shouldGetCorrectAuthor() {
        Author author = authorService.findById(AUTHOR_ONE_ID);
        assertEquals(AUTHOR_ONE_ID, author.getId());
        assertEquals(AUTHOR_ONE_NAME, author.getName());
    }


    @DisplayName("Should find all Authors")
    @Test
    void ShouldGetAllAuthors() {
        val authors = authorService.findAll();
        assertThat(authors).isNotNull().hasSize(EXPECTED_AUTHORS_COUNT)
                .allMatch(s -> s.getId() > 0)
                .allMatch(s -> !s.getName().equals(""));
    }


    @DisplayName("Should be able to insert a Author-1 after deletions")
    @Test
    void shouldAddNewAuthor() {
        Author savedAouthor = authorService.create(AUTHOR_ONE_NAME_NEW);
        assertThat(savedAouthor.getId()).isGreaterThan(0L);
        assertEquals(AUTHOR_ONE_NAME_NEW, authorService.findById(savedAouthor.getId()).getName());
    }


    @DisplayName("добавлять автора")
    @Test
    void shouldUpdateAuthor() {
        authorService.save(AUTHOR_ONE_ID, AUTHOR_ONE_NAME_NEW);
        Author authorNew = em.find(Author.class, AUTHOR_ONE_ID);
        assertEquals(AUTHOR_ONE_UPDATED, authorNew);
    }


    @DisplayName("Should be able to delete a Author:")
    @Test
    void shouldDeletefirstAuthor() {
        Author author = authorService.findById(AUTHOR_ONE_ID);
        assertEquals(AUTHOR_ONE_NAME, author.getName());
        // DELETE:
        authorService.delete(author.getId());
        assertThatCode(() -> authorService.findById(AUTHOR_ONE_ID))
                .isInstanceOf(RuntimeException.class);
    }



}