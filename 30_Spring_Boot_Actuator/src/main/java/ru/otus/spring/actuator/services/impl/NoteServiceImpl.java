package ru.otus.spring.actuator.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.actuator.domain.Book;
import ru.otus.spring.actuator.domain.Note;
import ru.otus.spring.actuator.exceptions.OtusException;
import ru.otus.spring.actuator.repository.BookRepository;
import ru.otus.spring.actuator.repository.NoteRepository;
import ru.otus.spring.actuator.services.NoteService;


import java.util.List;


@Component
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final BookRepository bookRepository;
    private final NoteRepository noteRepository;
    private static final String NOTE_NOT_EXIST = "Wasn't able to find note with this ID.";
    private static final String BOOK_NOT_EXIST = "Wasn't able to find book with this ID.";


    @Transactional(readOnly = true)
    @Override
    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    
    @Transactional(readOnly = true)
    @Override
    public Note findById(long id) {
        Note note = noteRepository.findById(id).orElse(null);
        if (note == null) {
            throw new OtusException(NOTE_NOT_EXIST);
        }
        return note;
    }


    @Override
    public List<Note> findByBookId(long bookId) {
        return noteRepository.findByBookId(bookId);
    }

    @Transactional
    @Override
    public Note save(long id, String newNote) {
        Note note = findById(id);
        if (note == null) {
            throw new OtusException(NOTE_NOT_EXIST);
        }
        note.setNote(newNote);
        return noteRepository.save(note);
    }

    @Override
    public Note save(Note note) {
        if (note == null) {
            throw new OtusException(NOTE_NOT_EXIST);
        }
        return noteRepository.save(note);
    }

    @Transactional
    @Override
    public long create(Long bookId, String noteStr) {
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book == null) {
            throw new OtusException(BOOK_NOT_EXIST);
        }
        Note note = new Note(0, book, noteStr);
        return noteRepository.save(note).getId();
    }


    @Transactional
    @Override
    public void delete(long id) {
        Note note = findById(id);
        noteRepository.delete(note);
    }

}
