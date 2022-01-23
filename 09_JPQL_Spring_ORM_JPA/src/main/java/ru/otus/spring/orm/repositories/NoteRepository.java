package ru.otus.spring.orm.repositories;

import ru.otus.spring.orm.domain.Genre;
import ru.otus.spring.orm.domain.Note;

import java.util.List;
import java.util.Optional;

public interface NoteRepository {

    Optional<Note> getNoteById(long id);

    Note save(Note note);

    List<Note> getAllNote();

    List<Note> getNoteByBookId(long noteId);

    long countNote();

    void delete(Note note);

}
