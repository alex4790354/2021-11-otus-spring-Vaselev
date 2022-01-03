package ru.otus.spring.dao;


import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.customExceptions.DaoException;
import ru.otus.spring.dao.interfaces.AuthorDao;
import ru.otus.spring.dao.interfaces.BookDao;
import ru.otus.spring.dao.interfaces.GenreDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations npJdbc;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    public BookDaoJdbc(NamedParameterJdbcOperations npJdbc, AuthorDao authorDao, GenreDao genreDao) {
        this.npJdbc = npJdbc;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    // wasn't able to get count using npJdbc
    @Override
    public int getCount() {
        Map<String, Object> params = Collections.emptyMap();
        return npJdbc.queryForObject("SELECT count(*) FROM book ", params, Integer.class);
    }

    @Override
    public List<Book> getAll() {
        return npJdbc.query("SELECT id, author_id, genre_id, name FROM book ", new BookMapper(authorDao, genreDao));
    }

    @Override
    public Book getById(int id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return npJdbc.queryForObject("SELECT id, author_id, genre_id, name FROM book WHERE id = :id", params, new BookMapper(authorDao, genreDao));
    }

    @Override
    public void deleteById(int id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        npJdbc.update("DELETE FROM book WHERE id = :id", params);
    }

    @Override
    public void updateById(int id, String newName) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("book_name", newName);
        npJdbc.update("UPDATE book SET name = :book_name WHERE id = :id", params);
    }

    @Override
    //public void insert(String authorName, String genreName, String bookName) throws WrongSqlStatement {
    public void insert(Book book) throws DaoException {
        Map<String, Object> params = new HashMap<>();
        params.put("author_id", book.getAuthor().getId());
        params.put("genre_id", book.getGenre().getId());
        params.put("book_name", book.getName());
        try {
            npJdbc.update("INSERT INTO book(author_id, genre_id, name) VALUES (:author_id, :genre_id, :book_name )", params);
        } catch (Exception exc) {
            if (exc.getClass().toString().toLowerCase().contains("dataintegrityviolation")) {
                throw new DaoException("Error: Author or genre doesn't exist. Please check and correct it");
            } else {
                exc.printStackTrace();
            }
        }
    }


    private static class BookMapper implements RowMapper<Book> {
        private final AuthorDao authorDao;
        private final GenreDao genreDao;
        public BookMapper(AuthorDao authorDao, GenreDao genreDao) {
            this.authorDao = authorDao;
            this.genreDao = genreDao;
        }

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            Author author = authorDao.getById(resultSet.getInt("author_id"));
            Genre genre = genreDao.getById(resultSet.getInt("genre_id"));
            String bookName = resultSet.getString("name");
            return new Book(id, author, genre, bookName);
        }
    }
}
