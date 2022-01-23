package ru.otus.spring.orm.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.orm.customExceptions.DaoException;
import ru.otus.spring.orm.domain.Book;
import ru.otus.spring.orm.domain.Note;
import ru.otus.spring.orm.repositories.BookRepository;
import ru.otus.spring.orm.repositories.NoteRepository;
import ru.otus.spring.orm.services.NoteService;
import java.util.List;


@Component
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final BookRepository bookRepository;
    private final NoteRepository noteRepository;
    private static final String NOTE_NOT_EXIST = "Wasn't able to find note with this ID.";
    private static final String BOOK_NOT_EXIST = "Wasn't able to find book with this ID.";


    @Transactional
    @SneakyThrows // Would be better do not use SneakyThrows at all.
    @Override
    public long create(Long bookId, String noteStr) {
        Book book = bookRepository.getBookById(bookId).orElse(null);
        if (book == null) {
            throw new DaoException(BOOK_NOT_EXIST, new RuntimeException());
        }
        Note note = new Note(0, book, noteStr);
        return noteRepository.save(note).getId();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Note> getAllNote() {
        return noteRepository.getAllNote();
    }

    @SneakyThrows // Would be better do not use SneakyThrows at all.
    @Transactional(readOnly = true)
    @Override
    public Note getNoteById(long id) {
        Note note = noteRepository.getNoteById(id).orElse(null);
        if (note == null) {
            throw new DaoException(NOTE_NOT_EXIST, new RuntimeException());

        }
        return note;
    }

    @SneakyThrows // Would be better do not use SneakyThrows at all.
    @Transactional
    @Override
    public void update(long id, String newNote) {
        Note note = getNoteById(id);
        if (note == null) {
            throw new DaoException(NOTE_NOT_EXIST, new RuntimeException());
        }
        note.setNote(newNote);
        noteRepository.save(note);
    }

    @Transactional
    @Override
    public void delete(long id) {
        Note note = getNoteById(id);
        noteRepository.delete(note);
    }

}
