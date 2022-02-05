package ru.otus.spring.jquery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BookDto {

    private Long id;

    private String title;

    private Long authorId;

    private Long genreId;
}
