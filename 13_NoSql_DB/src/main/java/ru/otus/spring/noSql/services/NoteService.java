package ru.otus.spring.noSql.services;

import ru.otus.spring.noSql.domain.Note;

import java.util.List;

public interface NoteService {

    long create(Long bookId, String review);

    List<Note> findAll();

    Note findById(long id);

    void save(long id, String newNote);

    void delete(long id);

}
