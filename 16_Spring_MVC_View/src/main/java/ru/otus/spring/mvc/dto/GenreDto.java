package ru.otus.spring.mvc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genre {

    private long id;

    @NotBlank(message = "{name-field-should-not-be-blank}")
    @Size(min = 5, max = 100, message = "{name-field-should-has-expected-size}")
    private String name;

    public Genre(String name) {
        this.name = name;
    }
}
