package ru.otus.spring.noSql.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.noSql.domain.Author;
import ru.otus.spring.noSql.domain.Book;
import ru.otus.spring.noSql.domain.Note;
import ru.otus.spring.noSql.repositories.AuthorRepository;
import ru.otus.spring.noSql.repositories.BookRepository;
import ru.otus.spring.noSql.repositories.CommentRepository;
import ru.otus.spring.noSql.repositories.GenreRepository;
import ru.otus.spring.noSql.service.BookService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    static final String NULL_BOOK = "Didn't find book";

    @Override
    @Transactional
    public void addBook(String title, String authorId, String genreId) {
        Book book = new Book(title,
                authorRepository.findById(authorId).get(),
                genreRepository.findByName(genreId).get());

        bookRepository.save(book);

    }

    @Override
    public void deleteBook(String title) {
        Book book = bookRepository.findByTitle(title).orElseThrow();
        bookRepository.delete(book);
    }

    @Override
    public void saveBook(Book book) {
        if (book != null) {
            bookRepository.save(book);
        } else {
            throw new RuntimeException(NULL_BOOK);
        }
    }

    @Override
    public Book getBookByTitle(String title) {
        return bookRepository.findByTitle(title).orElseThrow();
    }


    @Override
    public List<Note> getBookComments(String title) {
        Book book = bookRepository.findByTitle(title).orElseThrow();

        return commentRepository.findAllByBookId(book.getId());
    }

    @Override
    public long countBooks() {
        return bookRepository.count();
    }

    @Override
    public List<Note> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public boolean addBookComment(String title, String content) {
        Book book = bookRepository.findByTitle(title).orElseThrow();

        Note note = new Note(content, book.getId());
        Note save = commentRepository.save(note);

        System.out.println("Saved with id " + save.getId());
        return true;
    }

    @Override
    public void changeBookComment(String title, String commentContent) {

        Book book = bookRepository.findById(title).orElseThrow();
        Note note =
                book.getNotes().get(0);
        Note newNote = new Note(note.getId(), commentContent, note.getBookId());
        commentRepository.save(newNote);
        System.out.println("comment was : " + note);
        System.out.println("comment now : " + note);
    }


    @Override
    public boolean deleteBookComment(String commentId) {
        Note note = commentRepository.findById(commentId).orElseThrow();
        commentRepository.delete(note);
        System.out.println("success delete comment " + commentId);
        return true;
    }

    @Override
    public List<Book> getBooksByAuthor(Author author) {
        return bookRepository.findByAuthor(author);
    }

    @Override
    public List<Book> getAllBooks() {
        System.out.println("start searching for books");
        return bookRepository.findAll();
    }
}
