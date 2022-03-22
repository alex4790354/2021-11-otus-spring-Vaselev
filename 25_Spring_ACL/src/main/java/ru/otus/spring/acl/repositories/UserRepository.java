package ru.otus.spring.acl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.acl.domain.User;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
