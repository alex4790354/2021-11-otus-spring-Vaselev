package ru.otus.spring.orm.repositories.jpa;


import lombok.SneakyThrows;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.orm.customExceptions.DaoException;
import ru.otus.spring.orm.domain.Book;
import ru.otus.spring.orm.repositories.BookRepository;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;


@Repository
public class BookRepositoryJPA implements BookRepository {

    @PersistenceContext
    private final EntityManager em;

    public BookRepositoryJPA(EntityManager em) {
        this.em = em;
    }


    @Override
    public Optional<Book> findBookById(long id) {
        /*TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.id = :id", Book.class);
        query.setParameter("id", id);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }*/
        return Optional.ofNullable(em.find(Book.class, id));
    }

    //// CHECKED ^^


    @Transactional(readOnly = true)  // Здесь и далее - для примера, пока у нас нет сервисов.
    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b ", Book.class);
        return query.getResultList();
    }


    // Здесь и далее @Transactional - для примера, пока у нас нет сервисов.
    @Transactional(readOnly = true)
    @Override
    public Long getBooksCount() {
        TypedQuery<Long> query = em.createQuery("SELECT count(b) FROM Book b ", Long.class);
        return query.getSingleResult();
    }


    @Override
    public List<String> findAllBookNames() {
        TypedQuery<String> query = em.createQuery("SELECT b.title FROM Book b ", String.class);
        return query.getResultList();
    }


    @Override
    public List<Book> findBooksByAuthorName(String authorName) {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.author.name = :authorName", Book.class);
        query.setParameter("authorName", authorName);
        return query.getResultList();
    }


    @Override
    public List<Book> findBooksByGenreName(String genreName) {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.genre.name like :genreName", Book.class);
        query.setParameter("genreName", genreName);
        return query.getResultList();
    }


    @Override
    public List<Book> findBooksByName(String bookName) {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.title like :bookName", Book.class);
        query.setParameter("bookName", bookName);
        return query.getResultList();
    }


    @Override
    public int updateBookName(String oldBookName, String newBookName) {
        return em.createQuery(" UPDATE Book b " +
                " SET b.title = :newBookName " +
                " WHERE b.title = :oldBookName ")
            .setParameter("newBookName", newBookName)
            .setParameter("oldBookName", oldBookName)
            .executeUpdate();
    }


    @Override
    public Book updateBook(Book newBook) {
        if (newBook.getId() <= 0) {
            em.persist(newBook);
            return newBook;
        } else {
            return em.merge(newBook);
        }
    }


    @Override
    public int deleteById(long id) {
        int result = em.createQuery(" DELETE FROM Book b WHERE b.id = :id ")
            .setParameter("id", id)
            .executeUpdate();
        // TODO: check: should I do it or not:
        findBookById(id).ifPresent(em::remove);
        return result;
    }


    @SneakyThrows
    @Override
    public void insertNewBook(Book newBook) {
        try {
            em.merge(newBook);
        } catch (Exception e) {
            if (e.getClass().equals(DataIntegrityViolationException.class)) {
                throw new DaoException("Unexpected exception during book insertion.", e);
            } else {
                throw e;
            }
        }
    }

}
