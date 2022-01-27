package ru.otus.spring.mvc.dto;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static javax.persistence.FetchType.LAZY;


@Data
@EqualsAndHashCode(exclude = "book")
@AllArgsConstructor
@NoArgsConstructor
public class Note {

    private long id;

    @ToString.Exclude
    private BookDto book;

    @NotBlank(message = "{name-field-should-not-be-blank}")
    @Size(min = 5, max = 200, message = "{name-field-should-has-expected-size}")
    private String note;

    public Note(BookDto book, String note) {
        this.book = book;
        this.note = note;
    }

}
