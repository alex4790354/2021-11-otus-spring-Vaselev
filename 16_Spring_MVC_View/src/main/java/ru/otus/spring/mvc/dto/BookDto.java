package ru.otus.spring.mvc.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.spring.mvc.domain.Author;
import ru.otus.spring.mvc.domain.Genre;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private long id;

    private Author author;

    private Genre genre;

    @NotBlank(message = "{name-field-should-not-be-blank}")
    @Size(min = 5, max = 100, message = "{book-field-should-has-expected-size}")
    private String title;

    private long notesCount;


}
