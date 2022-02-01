package ru.otus.spring.jquery.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.pk.spring.controller.dto.CommentDto;
import ru.otus.pk.spring.domain.Book;
import ru.otus.pk.spring.domain.Comment;
import ru.otus.pk.spring.domain.mapper.CommentMapper;
import ru.otus.pk.spring.service.BookService;
import ru.otus.pk.spring.service.CommentService;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.CREATED;

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class CommentController {

    private final BookService bookService;
    private final CommentService service;
    private final CommentMapper mapper;

    @GetMapping("/book/{bookId}/comments")
    public List<CommentDto> findByBookId(@PathVariable("bookId") Long bookId) {
        return service.findByBookId(bookId).stream().map(mapper::toDto).collect(toList());
    }

    @ResponseStatus(CREATED)
    @PostMapping("/comments")
    public Comment create(@RequestBody CommentDto dto) {
        Book book = bookService.findById(dto.getBookId());
        Comment comment = new Comment(null, dto.getText(), book);
        return service.save(comment);
    }

    @DeleteMapping("/comments/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.deleteById(id);
    }
}
