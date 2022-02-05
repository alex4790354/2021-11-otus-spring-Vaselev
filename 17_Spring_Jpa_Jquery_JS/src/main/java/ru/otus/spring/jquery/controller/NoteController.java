package ru.otus.spring.jquery.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.jquery.domain.Book;
import ru.otus.spring.jquery.domain.Note;
import ru.otus.spring.jquery.dto.NoteDto;
import ru.otus.spring.jquery.dto.mapper.NoteMapper;
import ru.otus.spring.jquery.service.BookService;
import ru.otus.spring.jquery.service.NoteService;
import java.util.List;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.CREATED;


@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class NoteController {

    private final BookService bookService;
    private final NoteService noteService;
    private final NoteMapper mapper;

    @GetMapping("/book/{bookId}/notes")
    public List<NoteDto> findByBookId(@PathVariable("bookId") Long bookId) {
        List<Note> notes = noteService.findByBookId(bookId);
        for (Note note : notes) {
            System.out.println(note.getId() + " + " + note.getNote());
        }
        return noteService.findByBookId(bookId).stream().map(mapper::toDto).collect(toList());
    }

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
