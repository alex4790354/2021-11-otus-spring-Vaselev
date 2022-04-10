package ru.otus.spring.actuator.services;

import ru.otus.spring.actuator.domain.Note;

import java.util.List;

public interface NoteService {

    long create(Long bookId, String review);

    List<Note> findAll();

    List<Note> findByBookId(long bookId);

    Note findById(long id);

    Note save(long id, String newNote);

    Note save(Note newNote);

    void delete(long id);

}
