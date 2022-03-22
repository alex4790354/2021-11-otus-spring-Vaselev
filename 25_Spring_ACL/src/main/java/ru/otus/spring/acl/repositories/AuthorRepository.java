package ru.otus.spring.acl.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.acl.domain.Author;


public interface AuthorRepository extends JpaRepository<Author, Long> {


}
