package ru.otus.spring.security.services;

import ru.otus.spring.security.domain.Note;
import java.util.List;


public interface NoteService {

    long create(Long bookId, String review);

    List<Note> findAll();

    List<Note> findByBookId(long bookId);

    Note findById(long id);

    void save(long id, String newNote);

    void save(Note note);

    void delete(long id);

    long count(long bookId);

}
