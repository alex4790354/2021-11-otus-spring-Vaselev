package ru.otus.spring.batch.domain.jdbc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JdbcComment {
    private Long id;

    private String text;

    private Long bookId;
}
