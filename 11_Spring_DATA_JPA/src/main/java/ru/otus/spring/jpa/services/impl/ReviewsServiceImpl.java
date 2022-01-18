package ru.otus.spring.jpa.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.jpa.customExceptions.DaoException;
import ru.otus.spring.jpa.domain.Book;
import ru.otus.spring.jpa.domain.Review;
import ru.otus.spring.jpa.repositories.BookRepository;
import ru.otus.spring.jpa.repositories.ReviewRepository;
import ru.otus.spring.jpa.services.ReviewsService;

import java.util.List;


@Component
@RequiredArgsConstructor
public class ReviewsServiceImpl implements ReviewsService {

    private final BookRepository bookRepository;
    private final ReviewRepository reviewRepository;
    private static final String REVIEW_NOT_EXIST = "Wasn't able to find review with this ID.";
    private static final String BOOK_NOT_EXIST = "Wasn't able to find book with this ID.";


    @Transactional
    @SneakyThrows
    @Override
    public long create(Long bookId, String reviewStr) {
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book == null) {
            throw new DaoException(BOOK_NOT_EXIST, new RuntimeException());
        }
        Review review = new Review(0, book, reviewStr);
        return reviewRepository.save(review).getId();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @SneakyThrows
    @Transactional(readOnly = true)
    @Override
    public Review getReviewById(long id) {
        Review review = reviewRepository.findById(id).orElse(null);
        if (review == null) {
            throw new DaoException(REVIEW_NOT_EXIST, new RuntimeException());

        }
        return review;
    }

    @SneakyThrows
    @Transactional
    @Override
    public void update(long id, String newReview) {
        Review review = getReviewById(id);
        if (review == null) {
            throw new DaoException(REVIEW_NOT_EXIST, new RuntimeException());
        }
        review.setReview(newReview);
        reviewRepository.save(review);
    }

    @Transactional
    @Override
    public void delete(long id) {
        Review review = getReviewById(id);
        reviewRepository.delete(review);
    }

}
