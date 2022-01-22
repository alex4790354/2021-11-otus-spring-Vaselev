package ru.otus.spring.orm.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "Book")
@AllArgsConstructor
@NoArgsConstructor
// @RequiredArgsConstructor // TODO: try to change it
@Table(name = "book")
public class Book {

    public Book(Author author, Genre genre, String title) {
        this.author = author;
        this.genre = genre;
        this.title = title;
        //this.notes = null;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(optional = false, targetEntity = Author.class, cascade = CascadeType.DETACH)
    @JoinColumn(name = "author_id", nullable = false, referencedColumnName = "id")
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 5)
    private Author author;

    @ManyToOne(optional = false, targetEntity = Genre.class, cascade = CascadeType.DETACH)
    @JoinColumn(name = "genre_id", nullable = false, referencedColumnName = "id")
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 5)
    private Genre genre;

    @Column(name = "title")
    private String title;

    /*@Fetch(FetchMode.SUBSELECT)
    @BatchSize(size = 10)
    @ToString.Exclude
    @OneToMany (targetEntity = Note.class,
            cascade = CascadeType.ALL,
            mappedBy = "book",
            orphanRemoval = true,
            fetch = FetchType.LAZY) .
    private List<Note> notes;*/

    public Book(Author author, Genre genre, String title, List<Note> notes) {
        this.author = author;
        this.genre = genre;
        this.title = title;
        //this.notes = notes;
    }

    // TODO: remove List<Note> notes
    public Book(long id, Author author, Genre genre, String title, List<Note> notes) {
        this.id = id;
        this.author = author;
        this.genre = genre;
        this.title = title;
        //this.notes = notes;
    }



}
