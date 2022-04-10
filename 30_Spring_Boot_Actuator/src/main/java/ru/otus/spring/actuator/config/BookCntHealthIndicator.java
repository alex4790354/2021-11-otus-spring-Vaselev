package ru.otus.spring.actuator.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import ru.otus.spring.actuator.services.BookService;


@RequiredArgsConstructor
@Component
public class BookCntHealthIndicator implements HealthIndicator {

    private final BookService bookService;

    @Override
    public Health health() {
        Long booksCount = bookService.count();
        //boolean noBooks = booksCount == 0;
        return booksCount == 0 ?
                Health.down().withDetail("message", "No books!!!").build() :
                Health.up().withDetail("message", "There " + booksCount + " books in the library!").build();
    }
}
