package ru.otus.spring.mvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.spring.mvc.domain.Note;

import java.util.List;
import java.util.Optional;


public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByBookId(Long bookId);

    long countByBookId(long id);

}
