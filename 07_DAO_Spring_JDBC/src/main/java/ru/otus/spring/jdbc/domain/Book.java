package ru.otus.spring.jdbc.domain;


import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Book {

    private long id;
    private Author author;
    private Genre genre;
    private String name;

    public Book(Author author, Genre genre, String name) {
        this.author = author;
        this.genre = genre;
        this.name = name;
    }
}
