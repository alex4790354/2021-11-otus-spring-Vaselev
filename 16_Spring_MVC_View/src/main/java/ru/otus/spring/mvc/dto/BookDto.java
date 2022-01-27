package ru.otus.spring.mvc.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.spring.mvc.domain.Author;
import ru.otus.spring.mvc.domain.Book;
import ru.otus.spring.mvc.domain.Genre;
import ru.otus.spring.mvc.repositories.NoteRepository;

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
    @Size(min = 5, max = 200, message = "{name-field-should-has-expected-size}")
    private String title;

    private long notesCount;

    public BookDto(Author author, Genre genre, String title) {
        this.author = author;
        this.genre = genre;
        this.title = title;
    }

    public Book toDomainObject() {
        return new Book(this.id, this.author, this.genre, this.title);
    }

    public BookDto fromDomainObject(Book book, long bookCount) {
        return new BookDto(book.getId(), book.getAuthor(), book.getGenre(), book.getTitle(), bookCount);
    }

}
