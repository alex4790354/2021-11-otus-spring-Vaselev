package ru.otus.spring.batch.domain.sql;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;
import static javax.persistence.GenerationType.IDENTITY;

@NamedEntityGraph(name = "Book.Author.Genre", attributeNodes = {@NamedAttributeNode("author"), @NamedAttributeNode("genre")})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"author", "genre"})
@EqualsAndHashCode(exclude = {"author", "genre"})
@Entity
@Table(name = "book")
@SecondaryTable(name = "mongo_book", pkJoinColumns = @PrimaryKeyJoinColumn(name = "book_id"))
public class Book {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToOne(cascade = ALL)
    private Author author;

    @ManyToOne(cascade = ALL)
    private Genre genre;

    @Column(name = "mongo_id", table = "mongo_book")
    private String mongoId;
}
