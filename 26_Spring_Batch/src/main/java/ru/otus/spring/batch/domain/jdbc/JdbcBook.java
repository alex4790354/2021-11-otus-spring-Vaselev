package ru.otus.spring.batch.domain.jdbc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JdbcBook {
    private Long id;

    private String name;

    private Long authorId;

    private Long genreId;

    private String mongoId;
}
