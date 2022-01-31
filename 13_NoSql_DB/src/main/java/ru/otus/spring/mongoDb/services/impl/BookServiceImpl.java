package ru.otus.spring.mongoDb.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.mongoDb.domain.Author;
import ru.otus.spring.mongoDb.domain.Book;
import ru.otus.spring.mongoDb.domain.Genre;
import ru.otus.spring.mongoDb.repository.AuthorRepository;
import ru.otus.spring.mongoDb.repository.BookRepository;
import ru.otus.spring.mongoDb.repository.GenreRepository;
import ru.otus.spring.mongoDb.repository.NoteRepository;
import ru.otus.spring.mongoDb.services.BookService;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;
    private final NoteRepository noteRepository;

    @Override
    public String create(String name, String authorName, String genreName) {
        Author author = authorRepository.findByName(authorName);
        Genre genre = genreRepository.findByName(genreName);
        Book book = bookRepository.save(new Book(name, List.of(author), List.of(genre)));
        return book.getId();
    }

    @Override
    public void update(String oldName, String newName) {
        Book book = bookRepository.findByName(oldName);
        book.setName(newName);
        bookRepository.save(book);
    }

    @Override
    public Book findByName(String name) {
        return Optional.ofNullable(bookRepository.findByName(name))
                       .orElseThrow(() -> new RuntimeException(String.format("Book with name '%s' not found", name)));
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public void deleteByName(String name) {
        bookRepository.deleteByName(name);
    }

    @Override
    public List<Book> findByAuthor(String authorName) {
        Author author = authorRepository.findByName(authorName);
        return bookRepository.findAllByAuthors(author.getId());
    }

    @Override
    public List<Book> findByGenre(String genreName) {
        Genre genre = genreRepository.findByName(genreName);
        return bookRepository.findAllByGenres(genre.getId());
    }

    private Optional<Genre> findGenreByName(List<Genre> genres, String name) {
        return genres.stream()
                     .filter(genre -> genre.getName().equals(name))
                     .findFirst();
    }

    private Optional<Author> findAuthorByName(List<Author> authors, String name) {
        return authors.stream()
                      .filter(author -> author.getName().equals(name))
                      .findFirst();
    }


}
