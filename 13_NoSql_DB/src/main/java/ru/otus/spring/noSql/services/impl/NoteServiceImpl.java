package ru.otus.spring.noSql.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.noSql.customExceptions.DaoException;
import ru.otus.spring.noSql.domain.Book;
import ru.otus.spring.noSql.domain.Note;
import ru.otus.spring.noSql.repositories.BookRepository;
import ru.otus.spring.noSql.repositories.NoteRepository;
import ru.otus.spring.noSql.services.NoteService;


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
            throw new DaoException(NOTE_NOT_EXIST);
        }
        return note;
    }

    
    @Transactional
    @Override
    public void save(long id, String newNote) {
        Note note = findById(id);
        if (note == null) {
            throw new DaoException(NOTE_NOT_EXIST);
        }
        note.setNote(newNote);
        noteRepository.save(note);
    }


    @Transactional
    @Override
    public long create(Long bookId, String noteStr) {
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book == null) {
            throw new DaoException(BOOK_NOT_EXIST);
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
