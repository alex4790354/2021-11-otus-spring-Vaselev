package ru.otus.spring.orm.repositories;


import lombok.SneakyThrows;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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


    @Transactional(readOnly = true)
    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM bookEntity b ", Book.class);
        return query.getResultList();
    }


    @Transactional(readOnly = true)
    @Override
    public Long getBooksCount() {
        TypedQuery<Long> query = em.createQuery("SELECT count(b) FROM bookEntity b ", Long.class);
        return query.getSingleResult();
    }


    @Transactional(readOnly = true)
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


    @Transactional(readOnly = true)
    @Override
    public List<String> findAllBookNames() {
        TypedQuery<String> query = em.createQuery("SELECT b.name FROM bookEntity b ", String.class);
        return query.getResultList();
    }


    @Transactional(readOnly = true)
    @Override
    public List<Book> findBooksByAuthorName(String authorName) {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM bookEntity b WHERE b.author.name = :authorName", Book.class);
        query.setParameter("authorName", authorName);
        return query.getResultList();
    }


    @Transactional(readOnly = true)
    @Override
    public List<Book> findBooksByGenreName(String genreName) {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM bookEntity b WHERE b.genre.name like :genreName", Book.class);
        query.setParameter("genreName", genreName);
        return query.getResultList();
    }


    @Transactional(readOnly = true)
    @Override
    public List<Book> findBooksByName(String bookName) {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM bookEntity b WHERE b.name like :bookName", Book.class);
        query.setParameter("bookName", bookName);
        return query.getResultList();
    }


    @Transactional
    @Override
    public void updateBookName(String oldBookName, String newBookName) {
        Query query = em.createQuery(" UPDATE bookEntity b " +
                " SET b.name = :newBookName " +
                " WHERE b.name = :oldBookName ");
        query.setParameter("newBookName", newBookName);
        query.setParameter("oldBookName", oldBookName);
        query.executeUpdate();
    }


    @Transactional
    @Override
    public Book updateBook(Book newBook) {
        if (newBook.getId() <= 0) {
            em.persist(newBook);
            return newBook;
        } else {
            return em.merge(newBook);
        }
    }


    @Transactional
    @Override
    public void deleteById(long id) {
        Query query = em.createQuery(" DELETE FROM bookEntity b WHERE b.id = :id ");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Transactional
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
