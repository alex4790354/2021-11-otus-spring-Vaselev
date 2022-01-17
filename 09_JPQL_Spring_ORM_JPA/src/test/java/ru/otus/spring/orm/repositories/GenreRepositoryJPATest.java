package ru.otus.spring.orm.repositories;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.orm.domain.Genre;
import ru.otus.spring.orm.repositories.jpa.GenreRepositoryJpa;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("ORM JPA Genres repository testing.")
@DataJpaTest
@Import(GenreRepositoryJpa.class)
class GenreRepositoryJPATest {

    private final static int EXPECTED_GENRES_COUNT = 4;
    private final static long GENRE_ONE_ID = 1L;
    private final static String GENRE_ONE_NAME = "Роман";
    private final static String GENRE_ONE_NAME_NEW = "Роман - New";

    @Autowired
    private GenreRepositoryJpa genreRepositoryJpa;

    @DisplayName("Should get correct Genre")
    @Test
    void shouldGetCorrectGenre() {
        Optional<Genre> genre = genreRepositoryJpa.getGenreById(GENRE_ONE_ID);
        assertEquals(GENRE_ONE_ID, genre.get().getId());
        assertEquals(GENRE_ONE_NAME, genre.get().getName());
    }

    @DisplayName("Should find all Genres")
    @Test
    void ShouldGetAllGenres() {
        val genres = genreRepositoryJpa.getAllGenres();
        assertThat(genres).isNotNull().hasSize(EXPECTED_GENRES_COUNT)
                .allMatch(s -> s.getId() > 0)
                .allMatch(s -> !s.getName().equals(""));
    }

    @DisplayName("Should be able to delete a Genre:")
    @Test
    void shouldDeletefirstGenre() {
        Genre genre = genreRepositoryJpa.getGenreById(GENRE_ONE_ID).get();
        assertEquals(GENRE_ONE_NAME, genre.getName());
        // DELETE:
        genreRepositoryJpa.delete(genre);
        Optional<Genre> GenreOptional = genreRepositoryJpa.getGenreById(GENRE_ONE_ID);
        assertEquals(Optional.empty(), GenreOptional);
    }
    
    @DisplayName("Should be able to insert a Genre-1 after deletions")
    @Test
    void shouldAddNewGenre() {
        Genre genre = new Genre(0L, GENRE_ONE_NAME_NEW);
        Genre savedGenre = genreRepositoryJpa.save(genre);
        assertThat(savedGenre.getId()).isGreaterThan(0);
        assertEquals(GENRE_ONE_NAME_NEW, savedGenre.getName());
    }

}