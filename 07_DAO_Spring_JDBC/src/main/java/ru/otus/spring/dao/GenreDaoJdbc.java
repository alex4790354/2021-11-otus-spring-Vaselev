package ru.otus.spring.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.otus.spring.dao.interfaces.GenreDao;
import ru.otus.spring.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class GenreDaoJdbc implements GenreDao {

    private final JdbcOperations jdbc;

    public GenreDaoJdbc(JdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query("SELECT * FROM genre", new GenreMapper());
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
