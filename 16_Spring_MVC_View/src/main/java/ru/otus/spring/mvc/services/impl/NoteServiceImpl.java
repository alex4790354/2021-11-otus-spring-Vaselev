package ru.otus.spring.mvc.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.mvc.customExceptions.DaoException;
import ru.otus.spring.mvc.domain.Book;
import ru.otus.spring.mvc.domain.Note;
import ru.otus.spring.mvc.repositories.BookRepository;
import ru.otus.spring.mvc.repositories.NoteRepository;
import ru.otus.spring.mvc.services.NoteService;
import java.util.List;


@Service
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
    public List<Note> findByBookId(long bookId) {
        return noteRepository.findByBookId(bookId);
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


    @Transactional(readOnly = true)
    @Override
    public long count(long bookId) {
        return noteRepository.count();
    }


    @Transactional
    @Override
    public Note save(long id, String newNote) {
        Note note = findById(id);
        if (note == null) {
            throw new DaoException(NOTE_NOT_EXIST);
        }
        note.setNote(newNote);
        return noteRepository.save(note);
    }


    @Override
    public Note save(Note note) {
        return noteRepository.save(note);
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
        noteRepository.deleteById(id);
    }
}
