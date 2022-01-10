package ru.otus.spring.jdbc.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.jdbc.dao.interfaces.AuthorDao;
import ru.otus.spring.jdbc.domain.Author;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;


@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations npJdbc;

    @Autowired
    public AuthorDaoJdbc(NamedParameterJdbcOperations npJdbc) {
        this.npJdbc = npJdbc;
    }

    @Override
    public Author getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return npJdbc.queryForObject("SELECT id, name FROM author WHERE id = :id", params, new AuthorMapper());
    }

    @Override
    public List<Author> getAll() {
        return npJdbc.query("SELECT id, name FROM author ", new AuthorMapper());
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String firstLastName = resultSet.getString("name");
            return new Author(id, firstLastName);
        }
    }

}
