package ru.otus.spring.orm.repositories.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.spring.orm.domain.Review;
import ru.otus.spring.orm.repositories.ReviewRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class ReviewRepositoryJpa implements ReviewRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<Review> getReviewById(long id) {
        return Optional.ofNullable(em.find(Review.class, id));
    }

    @Override
    public List<Review> getAllReviews() {
        return em.createQuery("select c from Review c", Review.class).getResultList();
    }

    @Override
    public List<Review> getReviewsByBookId(long bookId) {
        TypedQuery<Review> query = em.createQuery("SELECT r FROM Review r WHERE r.book.id = :bookId", Review.class);
        query.setParameter("bookId", bookId);
        return query.getResultList();
    }

    @Override
    public Review save(Review review) {
        if (review.getId() == 0) {
            em.persist(review);
            return review;
        }
        return em.merge(review);
    }


    @Override
    public long countReviews() {
        return em.createQuery("select count(c) from Review c", Long.class).getSingleResult();
    }

    @Override
    public void delete(Review review) {
        em.remove(review);
    }


}
