package ru.otus.spring.mvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.mvc.domain.Note;
import java.util.List;


public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByBookId(Long bookId);

    long countByBookId(long id);

}
