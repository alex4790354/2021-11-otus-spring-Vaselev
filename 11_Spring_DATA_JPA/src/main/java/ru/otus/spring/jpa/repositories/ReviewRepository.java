package ru.otus.spring.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.jpa.domain.Review;


public interface ReviewRepository extends JpaRepository<Review, Long> {


}
