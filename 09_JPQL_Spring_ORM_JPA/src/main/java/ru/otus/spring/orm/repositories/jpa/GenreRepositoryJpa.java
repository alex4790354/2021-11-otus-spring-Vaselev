package ru.otus.spring.orm.repositories.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.spring.orm.domain.Genre;
import ru.otus.spring.orm.domain.Note;
import ru.otus.spring.orm.repositories.GenreRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
@Component
public class GenreRepositoryJpa implements GenreRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public List<Genre> getAllGenres() {
        return em.createQuery("select g from Genre g", Genre.class).getResultList();

    }

    @Override
    public Optional<Genre> getGenreById(long id) {
        return ofNullable(em.find(Genre.class, id));
    }

    @Override
    public Genre save(Genre genre) {
        if (genre.getId() == 0) {
            em.persist(genre);
            return genre;
        }
        return em.merge(genre);
    }

    @Override
    public void delete(Genre genre) {
        em.remove(genre);
    }
}
