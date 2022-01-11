package ru.otus.spring.orm.repositories;

import lombok.SneakyThrows;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import ru.otus.spring.orm.customExceptions.DaoException;
import ru.otus.spring.orm.domain.Book;
import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryORM implements BookRepository{

    @PersistenceContext
    private final EntityManager em;

    public BookRepositoryORM(EntityManager em) {
        this.em = em;
    }


    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM bookEntity b ", Book.class);
        return query.getResultList();
    }


    @Override
    public Long getBooksCount() {
        TypedQuery<Long> query = em.createQuery("SELECT count(b) FROM bookEntity b ", Long.class);
        return query.getSingleResult();
    }


    @Override
    public Optional<Book> findBookById(long id) {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM bookEntity b WHERE b.id = :id", Book.class);
        query.setParameter("id", id);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }


    @Override
    public List<String> findAllBookNames() {
        TypedQuery<String> query = em.createQuery("SELECT b.name FROM bookEntity b ", String.class);
        return query.getResultList();
    }


    @Override
    public List<Book> findBooksByAuthorName(String authorName) {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM bookEntity b WHERE b.author.name = :authorName", Book.class);
        query.setParameter("authorName", authorName);
        return query.getResultList();
    }


    @Override
    public List<Book> findBooksByGenreName(String genreName) {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM bookEntity b WHERE b.genre.name like :genreName", Book.class);
        query.setParameter("genreName", genreName);
        return query.getResultList();
    }


    @Override
    public List<Book> findBooksByName(String bookName) {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM bookEntity b WHERE b.name like :bookName", Book.class);
        query.setParameter("bookName", bookName);
        return query.getResultList();
    }


    @Override
    public void updateBookName(String oldBookName, String newBookName) {
        Query query = em.createQuery(" UPDATE bookEntity b " +
                " SET b.name = :newBookName " +
                " WHERE b.name = :oldBookName ");
        query.setParameter("newBookName", newBookName);
        query.setParameter("oldBookName", oldBookName);
        query.executeUpdate();
    }


    @Override
    public void updateBookById(Book newBook) {
        Query query = em.createQuery(" UPDATE bookEntity b " +
                " SET b.author = :author, " +
                " b.genre = :genre, " +
                " b.name = :newBookName " +
                " WHERE b.id = :id ");
        query.setParameter("author", newBook.getAuthor());
        query.setParameter("genre", newBook.getGenre());
        query.setParameter("newBookName", newBook.getName());
        query.setParameter("id", newBook.getId());
        query.executeUpdate();
    }


    @Override
    public void deleteById(long id) {
        Query query = em.createQuery(" DELETE FROM bookEntity b WHERE b.id = :id ");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @SneakyThrows
    @Override
    public void insertNewBook(Book newBook) {
        try {
            em.createNativeQuery("INSERT INTO book(author_id, genre_id, name) VALUES (?,?,?)")
                    .setParameter(1, newBook.getAuthor().getId())
                    .setParameter(2, newBook.getGenre().getId())
                    .setParameter(3, newBook.getName())
                    .executeUpdate();
        } catch (Exception e) {
            if (e.getClass().equals(DataIntegrityViolationException.class)) {
                throw new DaoException("Unexpected exception during book insertion.", e);
            } else {
                throw e;
            }
        }
    }

}
