package ru.otus.spring.mvc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.spring.mvc.domain.Genre;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenreDto {

    private long id;

    @NotBlank(message = "{name-field-should-not-be-blank}")
    @Size(min = 5, max = 50, message = "{genre-field-should-has-expected-size}")
    private String name;

    public GenreDto(String name) {
        this.name = name;
    }

    public Genre toDomainObject() {
        return new Genre(this.id, this.name);
    }

    public static GenreDto fromDomainObject(Genre genre) {
        return new GenreDto(genre.getId(), genre.getName());
    }

}
