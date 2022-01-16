package ru.otus.spring.orm.services;

import ru.otus.spring.orm.domain.Book;
import ru.otus.spring.orm.domain.Genre;
import ru.otus.spring.orm.domain.Review;

import java.util.List;

public interface ReviewsService {

    long create(Long bookId, String review);

    List<Review> getAllReviews();

    Review getReviewById(long id);

    void update(long id, String newReview);

    void delete(long id);

}
