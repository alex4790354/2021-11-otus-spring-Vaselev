package ru.otus.spring.mongoDb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.mongoDb.domain.Genre;


public interface GenreRepository extends MongoRepository<Genre, String> {

    Genre findByName(String name);
    void deleteByName(String name);

}
