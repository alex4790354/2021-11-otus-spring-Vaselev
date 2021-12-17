package ru.otus.spring.dao.interfaces;

import ru.otus.spring.domain.Author;
import java.util.List;

public interface AuthorDao {

    List<Author> getAll();

}
