package ru.otus.spring.actuator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.actuator.domain.Author;
import ru.otus.spring.actuator.domain.Book;
import ru.otus.spring.actuator.domain.Genre;
import ru.otus.spring.actuator.domain.Note;
import ru.otus.spring.actuator.dto.BookDto;
import ru.otus.spring.actuator.dto.NoteDto;
import ru.otus.spring.actuator.dto.mapper.NoteMapper;
import ru.otus.spring.actuator.services.AuthorService;
import ru.otus.spring.actuator.services.BookService;
import ru.otus.spring.actuator.services.GenreService;
import ru.otus.spring.actuator.services.NoteService;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.CREATED;


@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class BookController {

    private final BookService service;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final NoteService noteService;
    private final NoteMapper mapper;

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

    @GetMapping("/book/{bookId}/notes")
    public List<NoteDto> findByBookId(@PathVariable("bookId") Long bookId) {
        List<Note> notes = noteService.findByBookId(bookId);
        for (Note note : notes) {
            System.out.println(note.getId() + " + " + note.getNote());
        }
        return noteService.findByBookId(bookId).stream().map(mapper::toDto).collect(toList());
    }

}
