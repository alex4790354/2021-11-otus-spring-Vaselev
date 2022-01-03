package ru.otus.spring.dao;


import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.dao.interfaces.AuthorDao;
import ru.otus.spring.domain.Author;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations npJdbc;

    public AuthorDaoJdbc(NamedParameterJdbcOperations npJdbc) {
        this.npJdbc = npJdbc;
    }

    @Override
    public Author getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return npJdbc.queryForObject("SELECT id, name FROM genre WHERE id = :id", params, new AuthorMapper());
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
