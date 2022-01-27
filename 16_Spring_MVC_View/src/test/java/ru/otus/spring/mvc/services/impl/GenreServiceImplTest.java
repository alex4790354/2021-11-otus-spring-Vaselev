package ru.otus.spring.mvc.services.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.mvc.domain.Genre;
import ru.otus.spring.mvc.services.GenreService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("ORM JPA Genres repository testing.")
@DataJpaTest
@Import(GenreServiceImpl.class)
class GenreServiceImplTest {

    private final static int EXPECTED_GENRES_COUNT = 4;
    private final static long GENRE_ONE_ID = 1L;
    private final static String GENRE_ONE_NAME = "Роман";
    private final static String GENRE_ONE_NAME_NEW = "Роман - New";
    private final static Genre GENRE_NEW_UPDATED = new Genre(GENRE_ONE_ID, GENRE_ONE_NAME_NEW);

    @Autowired
    private GenreService genreService;

    @Autowired
    private TestEntityManager em;

    @DisplayName("Should get correct Genre")
    @Test
    void shouldGetCorrectGenre() {
        Genre genre = genreService.findById(GENRE_ONE_ID);
        assertEquals(GENRE_ONE_ID, genre.getId());
        assertEquals(GENRE_ONE_NAME, genre.getName());
    }

    @DisplayName("Should find all Genres")
    @Test
    void ShouldGetAllGenres() {
        val genres = genreService.findAll();
        assertThat(genres).isNotNull().hasSize(EXPECTED_GENRES_COUNT)
                .allMatch(s -> s.getId() > 0)
                .allMatch(s -> !s.getName().equals(""));
    }

    @DisplayName("добавлять автора")
    @Test
    void shouldUpdateAuthor() {
        genreService.save(GENRE_ONE_ID, GENRE_ONE_NAME_NEW);
        val authorNew = em.find(Genre.class, GENRE_ONE_ID);
        assertEquals(GENRE_NEW_UPDATED, authorNew);
    }


    @DisplayName("Should be able to delete a Genre:")
    @Test
    void shouldDeletefirstGenre() {
        val genre = em.find(Genre.class, GENRE_ONE_ID);
        assertEquals(GENRE_ONE_NAME, genre.getName());
        genreService.delete(GENRE_ONE_ID);
        assertThatCode(() -> genreService.findById(GENRE_ONE_ID))
                .isInstanceOf(RuntimeException.class);
    }
    
    @DisplayName("Should be able to insert a Genre-1 after deletions")
    @Test
    void shouldAddNewGenre() {
        long savedGenreId = genreService.create(GENRE_ONE_NAME_NEW);
        assertThat(savedGenreId).isGreaterThan(0);
        assertEquals(GENRE_ONE_NAME_NEW, genreService.findById(savedGenreId).getName());
    }



}