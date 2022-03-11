package ru.otus.pk.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.pk.spring.domain.Book;
import ru.otus.pk.spring.repository.BookRepository;
import ru.otus.pk.spring.repository.CommentRepository;

import static org.springframework.http.HttpStatus.CREATED;

@RequiredArgsConstructor
@RestController
public class BookController {

    private final BookRepository repository;
    private final CommentRepository commentRepository;

    @GetMapping("/books")
    public Flux<Book> all() {
        return repository.findAll();
    }

    @GetMapping("/books/{id}")
    public Mono<Book> byId(@PathVariable("id") String id) {
        return repository.findById(id);
    }

    @ResponseStatus(CREATED)
    @PostMapping("/books")
    public Mono<Book> add(@RequestBody Book book) {
        return repository.save(book);
    }

    @PutMapping("/books")
    public Mono<Book> update(@RequestBody Book book) {
        return repository.save(book);
    }

    @DeleteMapping("/books/{id}")
    public Mono<Void> delete(@PathVariable("id") String id) {
        return Mono.zip(commentRepository.deleteByBookId(id), repository.deleteById(id))
                .flatMap(result -> Mono.empty());
    }
}
