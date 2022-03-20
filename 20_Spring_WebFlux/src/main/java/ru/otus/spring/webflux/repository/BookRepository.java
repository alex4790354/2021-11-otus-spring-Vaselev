package ru.otus.spring.webflux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.spring.webflux.domain.Book;

public interface BookRepository extends ReactiveMongoRepository<Book, String>, BookRepositoryCustom {
}
