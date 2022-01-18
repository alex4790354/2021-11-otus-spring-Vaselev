package ru.otus.spring.jpa.repositories;

import ru.otus.spring.jpa.domain.Review;

import java.util.List;

public interface ReviewRepositoryCustom {

    List<Review> getReviewsByBookId1(long bookId);

}
