package ru.otus.spring.jdbc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.jdbc.dao.interfaces.GenreDao;
import ru.otus.spring.jdbc.domain.Genre;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;


@Repository
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcOperations npJdbc;

    @Autowired
    public GenreDaoJdbc(NamedParameterJdbcOperations npJdbc) {
        this.npJdbc = npJdbc;
    }

    @Override
    public Genre getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return npJdbc.queryForObject("SELECT id, name FROM genre WHERE id = :id", params, new GenreMapper());
    }

    @Override
    public List<Genre> getAll() {
        return npJdbc.query("SELECT * FROM genre", new GenreMapper());
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String genreName = resultSet.getString("name");
            return new Genre(id, genreName);
        }
    }
}
