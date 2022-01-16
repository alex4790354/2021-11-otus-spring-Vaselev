package ru.otus.spring.orm.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.orm.customExceptions.DaoException;
import ru.otus.spring.orm.domain.Book;
import ru.otus.spring.orm.domain.Genre;
import ru.otus.spring.orm.domain.Review;
import ru.otus.spring.orm.repositories.BookRepository;
import ru.otus.spring.orm.repositories.GenreRepository;
import ru.otus.spring.orm.repositories.ReviewRepository;
import ru.otus.spring.orm.services.GenreService;
import java.util.List;


@Component
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;
    private final ReviewRepository reviewRepository;
    private static final String GENRE_NOT_EXIST = "Didn't find genre";


    @Transactional
    @Override
    public long create(String name) {
        Genre genre = new Genre(0, name);
        return genreRepository.save(genre).getId();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Genre> getGenres() {
        return genreRepository.getGenres();
    }

    @SneakyThrows
    @Transactional(readOnly = true)
    @Override
    public Genre getGenreById(long id) {
        Genre genreById = genreRepository.getGenreById(id).orElse(null);
        if (genreById != null) {
            return genreById;
        }
        throw new DaoException(GENRE_NOT_EXIST, new RuntimeException());
    }

    @SneakyThrows
    @Transactional
    @Override
    public void update(long id, String name) {
        Genre genre = genreRepository.getGenreById(id).orElse(null);
        if (genre == null) {
            throw new DaoException(GENRE_NOT_EXIST, new RuntimeException());
        }
        genre.setName(name);
        List<Book> books = bookRepository.getBooksByGenreId(id);
        genreRepository.save(genre);
        for (Book book : books) {
            book.setGenre(genre);
            bookRepository.saveBook(book);
        }
    }

    @SneakyThrows
    @Transactional
    @Override
    public void delete(long genreId) {
        Genre genre = genreRepository.getGenreById(genreId).orElse(null);
        if (genre == null) {
            throw new DaoException(GENRE_NOT_EXIST, new RuntimeException());
        }

        List<Book> books = bookRepository.getBooksByGenreId(genreId);
        List<Review> reviews = null;
        for (Book book : books) {
            reviews = book.getReviews();
            if (reviews != null) {
                for (Review review : reviews) {
                    reviewRepository.delete(review);
                }
            }
            bookRepository.deleteBook(book);
        }
        genreRepository.delete(genre);
    }
}
