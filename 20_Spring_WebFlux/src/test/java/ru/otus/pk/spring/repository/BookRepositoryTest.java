package ru.otus.pk.spring.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.test.StepVerifier;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DisplayName("Репозиторий для работы с книгами должен ")
@DataMongoTest
class BookRepositoryTest {

    private static final Set<String> GENRES = Set.of("Genre1", "Genre2");
    private static final Set<String> AUTHOR_FIRST_NAMES = Set.of("AuthorF1", "AuthorF2");
    private static final Set<String> AUTHOR_LAST_NAMES = Set.of("AuthorL1", "AuthorL2");

    @Autowired
    private BookRepository repository;

    @DisplayName("получать всех авторов")
    @Test
    void shouldFindAllAuthors() {
        StepVerifier
                .create(repository.findAllAuthors())
                .assertNext(author -> {
                    assertNotNull(author.getId());
                    assertTrue(AUTHOR_FIRST_NAMES.contains(author.getFirstName()));
                    assertTrue(AUTHOR_LAST_NAMES.contains(author.getLastName()));
                })
                .assertNext(author -> {
                    assertNotNull(author.getId());
                    assertTrue(AUTHOR_FIRST_NAMES.contains(author.getFirstName()));
                    assertTrue(AUTHOR_LAST_NAMES.contains(author.getLastName()));
                })
                .expectComplete()
                .verify();
    }

    @DisplayName("получать все жанры")
    @Test
    void shouldFindAllGenres() {
        StepVerifier
                .create(repository.findAllGenres())
                .assertNext(genre -> {
                    assertNotNull(genre.getId());
                    assertTrue(GENRES.contains(genre.getName()));
                })
                .assertNext(genre -> {
                    assertNotNull(genre.getId());
                    assertTrue(GENRES.contains(genre.getName()));
                })
                .expectComplete()
                .verify();
    }
}