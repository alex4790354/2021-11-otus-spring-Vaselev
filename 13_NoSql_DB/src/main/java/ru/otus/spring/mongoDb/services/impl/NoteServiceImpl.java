package ru.otus.spring.mongoDb.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.mongoDb.domain.Book;
import ru.otus.spring.mongoDb.domain.Note;
import ru.otus.spring.mongoDb.repository.BookRepository;
import ru.otus.spring.mongoDb.repository.NoteRepository;
import ru.otus.spring.mongoDb.services.NoteService;
import java.util.List;


@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final BookRepository bookRepository;
    private final NoteRepository noteRepository;


    @Override
    public String addComment(String nameBook, String text) {
        Book book = bookRepository.findByName(nameBook);
        return noteRepository.save(new Note(book.getId(), text)).getId();
    }

    @Override
    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    @Override
    public List<Note> findNotesByBookName(String bookName) {
        Book book = bookRepository.findByName(bookName);
        return noteRepository.findByBookId(book.getId());
    }

    @Override
    public void deleteComment(String bookName, String comment) {
        Book book = bookRepository.findByName(bookName);
        List<Note> notes = noteRepository.findByBookIdAndAndContent(book.getId(), comment);
        for (Note note : notes) {
            noteRepository.deleteById(note.getId());
        }
    }
}
