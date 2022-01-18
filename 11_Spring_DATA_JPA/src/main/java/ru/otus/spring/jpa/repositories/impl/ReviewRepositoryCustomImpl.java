package ru.otus.spring.jpa.repositories.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.jpa.domain.Review;
import ru.otus.spring.jpa.repositories.ReviewRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class ReviewRepositoryCustomImpl implements ReviewRepositoryCustom {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public List<Review> getReviewsByBookId1(long bookId) {
        TypedQuery<Review> query = em.createQuery("SELECT r FROM Review r " +
                "WHERE r.book.id = :bookId", Review.class);
        query.setParameter("bookId", bookId);
        return query.getResultList();
    }
}
