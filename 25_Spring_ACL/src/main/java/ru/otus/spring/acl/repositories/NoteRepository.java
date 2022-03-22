package ru.otus.spring.acl.repositories;

import org.aspectj.weaver.ast.Not;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.acl.domain.Note;
import java.util.List;
import java.util.Optional;


public interface NoteRepository extends JpaRepository<Note, Long> {

    @Override
    List<Note> findAll();

    @EntityGraph(value = "comment-book-author-genre")
    @Override
    Optional<Note> findById(Long id);

    List<Note> findByBookId(Long bookId);

    long countByBookId(long id);

}
