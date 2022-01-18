package ru.otus.spring.jpa.services;

import ru.otus.spring.jpa.domain.Review;

import java.util.List;

public interface ReviewsService {

    long create(Long bookId, String review);

    List<Review> getAllReviews();

    Review getReviewById(long id);

    void update(long id, String newReview);

    void delete(long id);

}
