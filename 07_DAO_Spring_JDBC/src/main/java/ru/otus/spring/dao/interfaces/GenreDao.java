package ru.otus.spring.dao.interfaces;

import ru.otus.spring.domain.Genre;
import java.util.List;

public interface GenreDao {

    List<Genre> getAll();

}
