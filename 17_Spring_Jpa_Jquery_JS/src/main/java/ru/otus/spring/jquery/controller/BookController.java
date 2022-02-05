package ru.otus.spring.jquery.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.jquery.domain.Author;
import ru.otus.spring.jquery.domain.Book;
import ru.otus.spring.jquery.domain.Genre;
import ru.otus.spring.jquery.dto.BookDto;
import ru.otus.spring.jquery.service.AuthorService;
import ru.otus.spring.jquery.service.BookService;
import ru.otus.spring.jquery.service.GenreService;
import java.util.List;
import static org.springframework.http.HttpStatus.CREATED;


@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class BookController {

    private final BookService service;
    private final AuthorService authorService;
    private final GenreService genreService;

    @GetMapping("/books")
    public List<Book> finAll() {

        return service.findAll();
    }

    @GetMapping("/books/{id}")
    public Book findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @ResponseStatus(CREATED)
    @PostMapping("/books")
    public Book create(@RequestBody BookDto dto) {
        System.out.println("create. ");
        Author author = authorService.findById(dto.getAuthorId());
        Genre genre = genreService.findById(dto.getGenreId());
        Book book = new Book(0L, author, genre, dto.getTitle());
        return service.save(book);
    }

    @PutMapping(value = "/books")
    public Book update(@RequestBody BookDto bookDto) {
        System.out.println("update. ");
        Book book = service.findById(bookDto.getId());
        book.setTitle(bookDto.getTitle());
        book.setAuthor(authorService.findById(bookDto.getAuthorId()));
        book.setGenre(genreService.findById(bookDto.getGenreId()));
        return service.save(book);
    }

    @DeleteMapping("/books/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

}
