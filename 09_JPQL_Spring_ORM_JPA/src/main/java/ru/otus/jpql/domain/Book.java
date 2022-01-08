package ru.otus.jpql.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "bookEntity")
@Table(name = "book")
public class Book {

    public Book(Author author, Genre genre, String name) {
        this.author = author;
        this.genre = genre;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id", nullable = false, referencedColumnName = "id")
    private Author author;

    @ManyToOne(optional = false)
    @JoinColumn(name = "genre_id", nullable = false, referencedColumnName = "id")
    private Genre genre;

    @Column(name = "name")
    private String name;

}
