package ru.otus.spring.jpa.services;

import ru.otus.spring.jpa.domain.Note;

import java.util.List;

public interface NoteService {

    long create(Long bookId, String review);

    List<Note> findAll();

    Note findById(long id);

    void save(long id, String newNote);

    void delete(long id);

}
