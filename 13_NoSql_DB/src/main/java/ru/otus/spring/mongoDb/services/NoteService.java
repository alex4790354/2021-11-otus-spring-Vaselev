package ru.otus.spring.mongoDb.services;

import ru.otus.spring.mongoDb.domain.Author;
import ru.otus.spring.mongoDb.domain.Note;

import java.util.List;

public interface NoteService {

    String addComment(String nameBook, String text);

    List<Note> findAll();
    List<Note> findNotesByBookName(String bookId);

    void deleteComment(String bookName, String note);

}
