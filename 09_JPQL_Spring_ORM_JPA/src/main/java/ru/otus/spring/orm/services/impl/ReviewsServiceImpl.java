package ru.otus.spring.orm.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.orm.customExceptions.DaoException;
import ru.otus.spring.orm.domain.Book;
import ru.otus.spring.orm.domain.Review;
import ru.otus.spring.orm.repositories.BookRepository;
import ru.otus.spring.orm.repositories.ReviewRepository;
import ru.otus.spring.orm.services.ReviewsService;
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
        Book book = bookRepository.getBookById(bookId).orElse(null);
        if (book == null) {
            throw new DaoException(BOOK_NOT_EXIST, new RuntimeException());
        }
        Review review = new Review(0, book, reviewStr);
        Review newReviewId = reviewRepository.save(review);
        List<Review> reviewsAfterCreate = reviewRepository.getReviewsByBookId(book.getId());
        book.setReviews(reviewsAfterCreate);
        return newReviewId.getId();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.getReviews();
    }

    @SneakyThrows
    @Transactional(readOnly = true)
    @Override
    public Review getReviewById(long id) {
        Review review = reviewRepository.getReviewById(id).orElse(null);
        if (review != null) {
            return review;
        } else {
            throw new DaoException(REVIEW_NOT_EXIST, new RuntimeException());
        }
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

        Book book = bookRepository.getBookById(review.getBook().getId()).orElse(null);
        if (book == null) {
            throw new DaoException(BOOK_NOT_EXIST, new RuntimeException());
        }
        List<Review> reviews = reviewRepository.getReviewsByBookId(review.getBook().getId());
        book.setReviews(reviews);
        bookRepository.saveBook(book);
    }

    @Transactional
    @Override
    public void delete(long id) {
        Review review = getReviewById(id);
        Book book = review.getBook();
        reviewRepository.delete(review);
        List<Review> reviesAfterDel = reviewRepository.getReviewsByBookId(book.getId());
        if (reviesAfterDel == null) {
            book.setReviews(null);
        } else {
            book.setReviews(reviesAfterDel);
        }
    }

}
