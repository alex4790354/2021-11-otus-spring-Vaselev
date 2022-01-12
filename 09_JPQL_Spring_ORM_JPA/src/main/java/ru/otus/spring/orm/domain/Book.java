package ru.otus.spring.orm.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

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

    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 10)
    @OneToMany (mappedBy = "book",
            targetEntity = Review.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Review> reviews;

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }


}
