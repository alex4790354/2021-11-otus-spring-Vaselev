package ru.otus.spring.orm.repositories.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.spring.orm.domain.Book;
import ru.otus.spring.orm.domain.Note;
import ru.otus.spring.orm.repositories.NoteRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;


@Component
@RequiredArgsConstructor
public class NoteRepositoryJpa implements NoteRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<Note> getNoteById(long id) {
        return ofNullable(em.find(Note.class, id));
        /*TypedQuery<Note> query = em.createQuery("SELECT n " +
                " FROM Note n " +
                //" JOIN FETCH n.book " +
                //" JOIN FETCH n.book.author " +
                //" JOIN FETCH n.book.genre " +
                " WHERE n.id = :id", Note.class);
        query.setParameter("id", id);*/
        //query.setHint("javax.persistence.fetchgraph", em.getEntityGraph("comment-book-author-genre"));
        //return  Optional.ofNullable(query.getSingleResult());
        //return Optional.ofNullable(em.find(Note.class, id));
    }

    @Override
    public List<Note> getAllNote() {
        TypedQuery<Note> query = em.createQuery("select c from Note c", Note.class);
        //query.setHint("javax.persistence.fetchgraph", em.getEntityGraph("comment-book-author-genre"));
        return query.getResultList();
    }

    @Override
    public List<Note> getNoteByBookId(long bookId) {
        TypedQuery<Note> query = em.createQuery("SELECT r FROM Note r WHERE r.book.id = :bookId", Note.class);
        query.setParameter("bookId", bookId);
        //query.setHint("javax.persistence.fetchgraph", em.getEntityGraph("comment-book-author-genre"));
        return query.getResultList();
    }

    @Override
    public Note save(Note note) {
        if (note.getId() == 0) {
            em.persist(note);
            return note;
        }
        return em.merge(note);
    }


    @Override
    public long countNote() {
        return em.createQuery("select count(c) from Note c", Long.class).getSingleResult();
    }

    @Override
    public void delete(Note note) {
        em.remove(note);
    }


}
