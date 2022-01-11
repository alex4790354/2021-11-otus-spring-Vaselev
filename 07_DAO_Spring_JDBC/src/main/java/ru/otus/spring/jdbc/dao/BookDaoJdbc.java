package ru.otus.spring.jdbc.dao;


import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.jdbc.customExceptions.DaoException;
import ru.otus.spring.jdbc.dao.interfaces.BookDao;
import ru.otus.spring.jdbc.domain.Author;
import ru.otus.spring.jdbc.domain.Book;
import ru.otus.spring.jdbc.domain.Genre;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


@Repository
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations npJdbc;

    @Autowired
    public BookDaoJdbc(NamedParameterJdbcOperations npJdbc) {
        this.npJdbc = npJdbc;
    }


    @Override
    public int getCount() {
        Map<String, Object> params = Collections.emptyMap();
        return npJdbc.getJdbcOperations().queryForObject("SELECT count(*) FROM book ", Integer.class);
    }

    @Override
    public List<Book> getAll() {
        return npJdbc.query("SELECT b.id id, " +
                        " b.author_id author_id, " +
                        " b.genre_id genre_id, " +
                        " b.name book_name, " +
                        " a.name author_name, " +
                        " g.name genre_name " +
                        " FROM book b " +
                        " INNER JOIN author a ON a.id = b.author_id " +
                        " INNER JOIN genre  g ON b.genre_id = g.id ",
                new BookMapper());
    }

    @Override
    public Book getById(long bookId) {
        Map<String, Object> params = Collections.singletonMap("book_id", bookId);
        return npJdbc.queryForObject("SELECT b.id id, " +
                " b.author_id author_id, " +
                " b.genre_id genre_id, " +
                " b.name book_name, " +
                " a.name author_name, " +
                " g.name genre_name " +
                " FROM book b" +
                " INNER JOIN author a ON a.id = b.author_id " +
                " INNER JOIN genre  g ON b.genre_id = g.id " +
                " WHERE b.id = :book_id ", params, new BookMapper());
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        npJdbc.update("DELETE FROM book WHERE id = :id", params);
    }

    @Override
    public void updateNameById(long id, String newName) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("book_name", newName);
        npJdbc.update("UPDATE book SET name = :book_name WHERE id = :id", params);
    }

    @Override
    public void updateById(Book newBook) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", newBook.getId());
        params.put("author_id", newBook.getAuthor().getId());
        params.put("genre_id", newBook.getGenre().getId());
        params.put("book_name", newBook.getName());
        npJdbc.update("UPDATE book SET author_id = :author_id, genre_id = :genre_id, name = :book_name WHERE id = :id", params);
    }

    @SneakyThrows
    @Override
    public void insert(Book book)  {
        Map<String, Object> params = new HashMap<>();
        params.put("author_id", book.getAuthor().getId());
        params.put("genre_id", book.getGenre().getId());
        params.put("book_name", book.getName());
        try {
            npJdbc.update("INSERT INTO book(author_id, genre_id, name) VALUES (:author_id, :genre_id, :book_name )", params);
        } catch (Exception e) {
            if (e.getClass().equals(DataIntegrityViolationException.class)) {
                throw new DaoException("Unexpected exception during book insertion.", e);
            } else {
                throw e;
            }
        }
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            Author author = new Author(resultSet.getLong("author_id"), resultSet.getString("author_name"));
            Genre genre = new Genre(resultSet.getLong("genre_id"), resultSet.getString("genre_name"));
            return new Book(resultSet.getLong("id"), author, genre, resultSet.getString("book_name"));
        }
    }

}
