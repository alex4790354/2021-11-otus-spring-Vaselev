package ru.otus.spring.domain;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Table(name = "book")
public class Book {

    public Book(long id, Author author, Genre genre, String name) {
        this.id = id;
        this.author = author;
        this.genre = genre;
        this.name = name;
    }

    public Book(Author author, Genre genre, String name) {
        this.author = author;
        this.genre = genre;
        this.name = name;
    }

    public Book() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    private Author author;
    private Genre genre;

    @Column(name = "name")
    private String name;

}
