package ru.otus.spring.orm.repositories;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.orm.domain.Author;
import ru.otus.spring.orm.domain.Book;
import ru.otus.spring.orm.domain.Genre;
import ru.otus.spring.orm.domain.Review;
import ru.otus.spring.orm.repositories.jpa.BookRepositoryJpa;
import ru.otus.spring.orm.repositories.jpa.ReviewRepositoryJpa;
import java.util.ArrayList;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@DisplayName("ORM JPA Genres repository testing.")
@DataJpaTest
@Import({ReviewRepositoryJpa.class, BookRepositoryJpa.class})
class ReviewRepositoryJPATest {

    private final static int EXPECTED_REVIEWS_COUNT = 27;
    private final static long REVIEW_ONE_ID = 1L;
    private final static String REVIEW_ONE_CONTEXT = "Review-01.1 - Мастер";
    private final static String REVIEW_ONE_CONTEXT_NEW = "Review-01.1 - Мастер - New";
    private final static Author AUTHOR_ONE = new Author(1, "Михаил Булгаков");
    private final static Genre GENRE_ONE = new Genre(1, "Роман");
    private final static String BOOK_ONE_NAME = "Мастер и Маргарита";
    private final static Book BOOK_ONE = new Book(1, AUTHOR_ONE, GENRE_ONE, BOOK_ONE_NAME, new ArrayList<>());

    @Autowired
    private ReviewRepository reviewRepository;


    @DisplayName("Should get correct Review by ID")
    @Test
    void shouldGetCorrectGenre() {
        Optional<Review> genre = reviewRepository.getReviewById(REVIEW_ONE_ID);
        assertEquals(REVIEW_ONE_ID, genre.get().getId());
        assertEquals(REVIEW_ONE_CONTEXT, genre.get().getReview());
    }

    @DisplayName("Should find all Reviews")
    @Test
    void ShouldGetAllGenres() {
        val genres = reviewRepository.getAllReviews();
        assertThat(genres).isNotNull().hasSize(EXPECTED_REVIEWS_COUNT)
                .allMatch(s -> s.getId() > 0)
                .allMatch(s -> !s.getReview().equals(""));
    }

    @DisplayName("Should be able to delete a Genre:")
    @Test
    void shouldDeletefirstGenre() {
        Optional<Review> review = reviewRepository.getReviewById(REVIEW_ONE_ID);
        assertNotNull(review.get());
        assertEquals(REVIEW_ONE_CONTEXT, review.get().getReview());
        // DELETE:
        reviewRepository.delete(review.get());
        Optional<Review> reviewAfterDelete = reviewRepository.getReviewById(REVIEW_ONE_ID);
        assertEquals(Optional.empty(), reviewAfterDelete);
    }

    @DisplayName("Should be able to insert a Genre-1 after deletions")
    @Test
    void shouldAddNewGenre() {
        Review review = new Review(0L, BOOK_ONE, REVIEW_ONE_CONTEXT_NEW);
        Review savedReview = reviewRepository.save(review);
        assertThat(savedReview.getId()).isGreaterThan(0);
        assertEquals(REVIEW_ONE_CONTEXT_NEW, savedReview.getReview());
    }

}