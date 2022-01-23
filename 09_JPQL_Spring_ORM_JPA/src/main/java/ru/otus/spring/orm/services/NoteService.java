package ru.otus.spring.orm.services;

import ru.otus.spring.orm.domain.Book;
import ru.otus.spring.orm.domain.Genre;
import ru.otus.spring.orm.domain.Note;

import java.util.List;

public interface NoteService {

    long create(Long bookId, String note);

    List<Note> getAllNote();

    Note getNoteById(long id);

    void update(long id, String newNote);

    void delete(long id);

}
