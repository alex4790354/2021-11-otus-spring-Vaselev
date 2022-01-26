package ru.otus.spring.noSql.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.noSql.domain.Note;

import java.util.List;
import java.util.Optional;


public interface CommentRepository extends CrudRepository<Note, Long> {

    Optional<Note> findById(String commentId);

    List<Note> findAll();

    List<Note> findAllByBookId(String bookId);

}
