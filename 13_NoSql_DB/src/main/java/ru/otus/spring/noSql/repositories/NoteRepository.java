package ru.otus.spring.noSql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.noSql.domain.Note;

import java.util.List;


public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByBookId(Long id);

}
