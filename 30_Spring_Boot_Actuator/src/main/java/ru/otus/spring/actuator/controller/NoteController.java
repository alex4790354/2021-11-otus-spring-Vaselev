package ru.otus.spring.actuator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.actuator.domain.Book;
import ru.otus.spring.actuator.domain.Note;
import ru.otus.spring.actuator.dto.NoteDto;
import ru.otus.spring.actuator.services.BookService;
import ru.otus.spring.actuator.services.NoteService;

import static org.springframework.http.HttpStatus.CREATED;


@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class NoteController {

    private final BookService bookService;
    private final NoteService noteService;

    @ResponseStatus(CREATED)
    @PostMapping("/notes")
    public Note create(@RequestBody NoteDto dto) {
        Book book = bookService.findById(dto.getBookId());
        Note note = new Note(0L, book, dto.getNote());
        return noteService.save(note);
    }

    @DeleteMapping("/notes/{id}")
    public void delete(@PathVariable("id") Long id) {
        noteService.delete(id);
    }

}
