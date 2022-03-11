package ru.otus.pk.spring.repository;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import reactor.test.StepVerifier;
import ru.otus.pk.spring.domain.Book;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DisplayName("Репозиторий для работы с комментариями должен ")
@DataMongoTest
class CommentRepositoryTest {
    private static final Set<String> NAMES = Set.of("Comment1", "Comment3");

    @Autowired
    private CommentRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @SneakyThrows
    @DisplayName("получать коментарии для книги")
    @Test
    void shouldReturnExpectedBookComments() {
        Book book1 = mongoTemplate.findAll(Book.class).stream()
                .filter(book -> book.getName().equals("Book1"))
                .findFirst().orElseThrow(Exception::new);

        StepVerifier
                .create(repository.findByBookId(book1.getId()))
                .assertNext(comment -> {
                    assertNotNull(comment.getId());
                    assertTrue(NAMES.contains(comment.getText()));
                })
                .assertNext(comment -> {
                    assertNotNull(comment.getId());
                    assertTrue(NAMES.contains(comment.getText()));
                })
                .expectComplete()
                .verify();
    }
}