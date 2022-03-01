package ru.otus.spring.mongoDb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.mongoDb.domain.Author;


public interface AuthorRepository extends MongoRepository<Author, String> {

    Author findByName(String name);
    void deleteByName(String name);

}
