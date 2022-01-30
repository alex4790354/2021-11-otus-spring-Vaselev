package ru.otus.spring.mvc.dto;

import lombok.*;
import ru.otus.spring.mvc.domain.Book;
import ru.otus.spring.mvc.domain.Note;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@EqualsAndHashCode(exclude = "book")
@AllArgsConstructor
@NoArgsConstructor
public class NoteDto {

    private long id;

    @ToString.Exclude
    private Book book;

    @NotBlank(message = "{name-field-should-not-be-blank}")
    @Size(min = 5, max = 100, message = "{note-field-should-has-expected-size}")
    private String note;


    public Note toDomainObject() {
        return new Note(this.id, this.book, this.note);
    }

    public static NoteDto fromDomainObject(Note note) {
        return new NoteDto(note.getId(), note.getBook(), note.getNote());
    }

}
