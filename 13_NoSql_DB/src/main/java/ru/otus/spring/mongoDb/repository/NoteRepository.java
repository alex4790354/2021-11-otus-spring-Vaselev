package ru.otus.spring.mongoDb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.mongoDb.domain.Note;
import java.util.List;


public interface NoteRepository extends MongoRepository<Note, String> {

    List<Note> findByBookIdAndAndContent(String bookId, String content);

    List<Note> findByBookId(String bookId);

    void deleteById(String id);

}
