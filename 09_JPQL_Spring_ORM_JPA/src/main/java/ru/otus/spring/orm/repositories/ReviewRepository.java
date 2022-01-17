package ru.otus.spring.orm.repositories;

import ru.otus.spring.orm.domain.Genre;
import ru.otus.spring.orm.domain.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {

    Optional<Review> getReviewById(long id);

    Review save(Review review);

    List<Review> getAllReviews();

    List<Review> getReviewsByBookId(long reviewId);

    long countReviews();

    void delete(Review review);

}
