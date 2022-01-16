package ru.otus.spring.orm.repositories.jpa;


import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;
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
    public Optional<Book> getBookById(long id) {

        return Optional.ofNullable(em.find(Book.class, id));

        /* //Second implementation. Not sure which would be better to use

        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.id = :id", Book.class);
        query.setParameter("id", id);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }*/
    }


    @Override
    public List<Book> getAllBooks() {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b ", Book.class);
        return query.getResultList();
    }

    @Override
    public List<Book> getBooksByStartName(String bookName) {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.title like :bookName", Book.class);
        query.setParameter("bookName", bookName + "%");
        return query.getResultList();
    }

    @Override
    public List<Book> getBooksByAuthorId(Long authorId) {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.author.id = :authorId", Book.class);
        query.setParameter("authorId", authorId);
        return query.getResultList();
    }

    @Override
    public List<Book> getBooksByGenreId(Long genreId) {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.genre.id = :genreId", Book.class);
        query.setParameter("genreId", genreId);
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


    @Override
    public int updateBookName(String oldBookName, String newBookName) {
        return em.createQuery(" UPDATE Book b " +
                " SET b.title = :newBookName " +
                " WHERE b.title = :oldBookName ")
            .setParameter("newBookName", newBookName)
            .setParameter("oldBookName", oldBookName)
            .executeUpdate();
    }

}
