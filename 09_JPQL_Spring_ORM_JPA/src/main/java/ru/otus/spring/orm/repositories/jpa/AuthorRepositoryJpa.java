package ru.otus.spring.orm.repositories.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.spring.orm.domain.Author;
import ru.otus.spring.orm.repositories.AuthorRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class AuthorRepositoryJpa implements AuthorRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<Author> getAuthorById(long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Override
    public List<Author> getAllAuthors() {
        return em.createQuery("select a from Author a", Author.class).getResultList();
    }


    @Override
    public Author save(Author author) {
        if (author.getId() == 0) {
            em.persist(author);
            return author;
        }
        return em.merge(author);
    }

    @Override
    public void delete(Author author) {
        em.remove(author);
    }
}
