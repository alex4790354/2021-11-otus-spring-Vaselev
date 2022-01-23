package ru.otus.spring.orm.repositories.jpa;


import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;
import ru.otus.spring.orm.customExceptions.DaoException;
import ru.otus.spring.orm.domain.Book;
import ru.otus.spring.orm.repositories.BookRepository;
import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Optional.ofNullable;


@RequiredArgsConstructor
@Repository
public class BookRepositoryJpa implements BookRepository {

    @PersistenceContext
    private final EntityManager em;


    @Override
    public Optional<Book> getBookById(long id) {
        Map<String, Object> properties = Map.of("javax.persistence.fetchgraph", em.getEntityGraph("book-author-genre"));
        return ofNullable(em.find(Book.class, id, properties));
    }


    @Override
    public List<Book> getAllBooks() {
        TypedQuery<Book> query = em.createQuery("SELECT b " +
                " FROM Book b " +
                " JOIN FETCH b.author " +
                " JOIN fetch b.genre ", Book.class);
        query.setHint("javax.persistence.fetchgraph", em.getEntityGraph("book-author-genre"));
        return query.getResultList();
    }


    @Override
    public Long getBooksCount() {
        TypedQuery<Long> query = em.createQuery("SELECT count(b) FROM Book b ", Long.class);
        return query.getSingleResult();
    }


    @Override
    public void deleteBook(Book book) {
        em.remove(book);
    }

    @SneakyThrows
    @Override
    public Book saveBook(Book newBook) {
        try {
            if (newBook.getId() == 0) {
                em.persist(newBook);
                return newBook;
            }
            return em.merge(newBook);
        } catch (Exception e) {
            throw new DaoException("Unexpected exception during book insertion.", e);
        }
    }

}
