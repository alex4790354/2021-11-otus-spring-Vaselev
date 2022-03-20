package ru.otus.spring.webflux.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import reactor.core.publisher.Flux;
import ru.otus.spring.webflux.domain.Author;
import ru.otus.spring.webflux.domain.Book;
import ru.otus.spring.webflux.domain.Genre;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    private final ReactiveMongoOperations reactiveMongoOperations;

    @Override
    public Flux<Author> findAllAuthors() {
        List<AggregationOperation> operations = List.of(
                group("author.id", "author.firstName", "author.lastName"),
                project().and("id").as("_id")
                        .and("firstName").as("firstName")
                        .and("lastName").as("lastName")
        );

        return reactiveMongoOperations.aggregate(newAggregation(operations), Book.class, Author.class);
    }

    @Override
    public Flux<Genre> findAllGenres() {
        List<AggregationOperation> operations = List.of(
                group("genre.id", "genre.name"),
                project().and("id").as("_id")
                        .and("name").as("name")
        );

        return reactiveMongoOperations.aggregate(newAggregation(operations), Book.class, Genre.class);
    }
}
