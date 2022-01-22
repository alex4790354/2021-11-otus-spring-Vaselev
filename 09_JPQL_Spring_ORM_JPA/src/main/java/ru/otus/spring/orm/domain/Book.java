package ru.otus.spring.orm.domain;


import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "Book")
@AllArgsConstructor
@NoArgsConstructor
@NamedEntityGraph(name = "book-author-genre",
        attributeNodes = {@NamedAttributeNode("author"),
                          @NamedAttributeNode("genre")})
@Table(name = "book")
public class Book {

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

    // Don't need to have 'List<Note> notes' in book. But can uncomment in later if need it
    /*@Fetch(FetchMode.SELECT)
    @BatchSize(size = 10)
    @ToString.Exclude
    @OneToMany (targetEntity = Note.class,
            cascade = CascadeType.ALL,
            mappedBy = "book",
            orphanRemoval = true,
            fetch = FetchType.LAZY) .
    private List<Note> notes;*/

}
