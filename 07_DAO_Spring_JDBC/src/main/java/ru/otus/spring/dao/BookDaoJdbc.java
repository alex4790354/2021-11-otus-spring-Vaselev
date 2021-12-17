package ru.otus.spring.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.customExceptions.WrongSqlStatement;
import ru.otus.spring.dao.interfaces.BookDao;
import ru.otus.spring.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations npJdbc;

    public BookDaoJdbc(JdbcOperations jdbc, NamedParameterJdbcOperations npJdbc) {
        this.npJdbc = npJdbc;
    }

    // wasn't able to get count using npJdbc
    @Override
    public int getCount() {
        //return jdbc.queryForObject("SELECT count(*) FROM book ", Integer.class);
        Map<String, Object> params = Collections.emptyMap();
        return npJdbc.queryForObject("SELECT count(*) FROM book ", params, Integer.class);
    }

    @Override
    public List<Book> getAll() {
        return npJdbc.query("SELECT * FROM book ", new BookMapper());
    }

    @Override
    public Book getById(int id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return npJdbc.queryForObject("SELECT * FROM book WHERE id = :id", params, new BookMapper());
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
        params.put("bookName", newName);
        npJdbc.update("UPDATE book SET name = :bookName WHERE id = :id", params);
    }

    @Override
    public void insert(String authorName, String genreName, String bookName) throws WrongSqlStatement {
        Map<String, Object> params = new HashMap<>();
        params.put("authorName", authorName);
        params.put("genreName", genreName);
        params.put("bookName", bookName);
        try {
            npJdbc.update("INSERT INTO book(id, author_id, genre_id, name) VALUES " +
                    "((SELECT max(id) + 1 FROM book)," +
                    " (SELECT id FROM author WHERE name = :authorName)," +
                    " (SELECT id FROM genre WHERE name = :genreName)," +
                    " :bookName )", params);
        } catch (Exception exc) {
            if (exc.getClass().toString().toLowerCase().contains("dataintegrityviolation")) {
                throw new WrongSqlStatement("Error: Author or genre doesn't exist. Please check and correct it");
            } else {
                exc.printStackTrace();
            }
        }
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            int authorId = resultSet.getInt("author_id");
            int genreId = resultSet.getInt("genre_id");
            String bookName = resultSet.getString("name");
            return new Book(id, authorId, genreId, bookName);
        }
    }
}
