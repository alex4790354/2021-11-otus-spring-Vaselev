package ru.otus.spring.noSql.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;


@Data

@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "BOOK")
public class Book {

    @Id
    private String id;

    @NotNull
    private String title;

    @DBRef
    private Author author;

    private Genre genre;

    @ToString.Exclude
    @DBRef
    private List<Note> notes;

    public Book(String title, Author author, Genre genre) {
        this(null, title, author, genre, null);
    }

    public Book(String id, String title, Author author, Genre genre) {
        this(id, title, author, genre, null);
    }

}


