package ru.otus.spring.orm.domain;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import static javax.persistence.FetchType.LAZY;


@Data
@EqualsAndHashCode(exclude = "book")
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Note")
@Table(name = "Note")
/*
Just as an example how we can use EntityGraph here:
@NamedEntityGraph(name = "comment-book-author-genre",
                    attributeNodes = {@NamedAttributeNode(value = "book", subgraph = "book-subgraph")}
                   ,subgraphs = {@NamedSubgraph(name = "book-subgraph",
                                                attributeNodes = {@NamedAttributeNode("genre"),
                                                                  @NamedAttributeNode("author")})
                                }
                  )*/
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(targetEntity = Book.class, fetch = LAZY)
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    private Book book;

    @NotBlank
    @Column(name = "note")
    private String note;

}
