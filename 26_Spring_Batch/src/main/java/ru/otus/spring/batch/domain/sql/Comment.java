package ru.otus.spring.batch.domain.sql;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@NamedEntityGraph(
        name = "Comment.Book.Author.Genre",
        attributeNodes = {
                @NamedAttributeNode(value = "book", subgraph = "book-subgraph"),
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "book-subgraph",
                        attributeNodes = {
                                @NamedAttributeNode("author"),
                                @NamedAttributeNode("genre")
                        }
                )
        }
)
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"book"})
@EqualsAndHashCode(exclude = {"book"})
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "time", nullable = false)
    private LocalDateTime time = now();

    @ManyToOne(fetch = LAZY)
    private Book book;

    public Comment(Long id, String text, Book book) {
        this.id = id;
        this.text = text;
        this.book = book;
    }
}
