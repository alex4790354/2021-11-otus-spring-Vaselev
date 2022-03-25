package ru.otus.spring.batch.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.batch.domain.sql.*;
import ru.otus.spring.batch.repository.AuthorRepository;
import ru.otus.spring.batch.repository.GenreRepository;

import java.util.*;

import static java.util.stream.Collectors.toSet;

@RequiredArgsConstructor
public class BookItemWriter<T> extends JpaItemWriter<T> {

    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Override
    public void write(List list) {
        List<Book> books = new ArrayList<Book>(list);
        Map<String, Author> authors = createAuthors(books);
        Map<String, Genre> genres = createGenres(books);
        books.forEach(book -> {
            book.setAuthor(authors.get(book.getAuthor().getMongoId()));
            book.setGenre(genres.get(book.getGenre().getMongoId()));
        });

        super.write(list);
    }

    @Transactional
    public Map<String, Author> createAuthors(List<Book> books) {
        Map<String, Author> authors = new HashMap<>();
        books.forEach(book -> authors.put(book.getAuthor().getMongoId(), book.getAuthor()));

        authorRepository.findByMongoIdIn(authors.keySet())
                .forEach(author -> authors.put(author.getMongoId(), author));

        Set<Author> newAuthors = authors.values().stream()
                .filter(author -> author.getId() == null).collect(toSet());
        authorRepository.saveAll(newAuthors)
                .forEach(author -> authors.put(author.getMongoId(), author));

        return authors;
    }

    @Transactional
    public Map<String, Genre> createGenres(List<Book> books) {
        Map<String, Genre> genres = new HashMap<>();
        books.forEach(book -> genres.put(book.getGenre().getMongoId(), book.getGenre()));

        genreRepository.findByMongoIdIn(genres.keySet())
                .forEach(genre -> genres.put(genre.getMongoId(), genre));

        Set<Genre> newGenres = genres.values().stream()
                .filter(genre -> genre.getId() == null).collect(toSet());
        genreRepository.saveAll(newGenres)
                .forEach(genre -> genres.put(genre.getMongoId(), genre));

        return genres;
    }
}
