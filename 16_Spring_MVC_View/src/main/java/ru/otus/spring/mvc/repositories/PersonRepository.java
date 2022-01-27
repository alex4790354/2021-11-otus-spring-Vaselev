package ru.otus.spring.mvc.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.mvc.domain.Person;
import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findAll();
}
