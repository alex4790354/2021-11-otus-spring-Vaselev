package ru.otus.spring.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.jpa.domain.Note;
import java.util.List;


public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByBookId(Long id);

}
