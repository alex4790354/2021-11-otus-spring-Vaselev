package ru.otus.spring.noSql.repositories.impl;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.noSql.domain.Note;
import ru.otus.spring.noSql.repositories.CommentRepository;
import ru.otus.spring.noSql.repositories.CustomBookRepository;

import java.util.List;

@RequiredArgsConstructor
public class CustomBookRepositoryImpl implements CustomBookRepository {

    private final CommentRepository commentRepository;

    @Override
    public void removeAllBooksComments(String bookId) {
        List<Note> notes = commentRepository.findAllByBookId(bookId);
        for (Note note : notes) {
            commentRepository.delete(note);
        }
    }
}
