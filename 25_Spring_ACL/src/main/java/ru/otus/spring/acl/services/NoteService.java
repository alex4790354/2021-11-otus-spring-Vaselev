package ru.otus.spring.acl.services;

import ru.otus.spring.acl.domain.Note;
import java.util.List;


public interface NoteService {

    long create(Long bookId, String review);

    List<Note> findAll();

    List<Note> findByBookId(long bookId);

    Note findById(long id);

    Note save(long id, String newNote);

    Note save(Note note);

    void delete(long id);

    long count(long bookId);

}
