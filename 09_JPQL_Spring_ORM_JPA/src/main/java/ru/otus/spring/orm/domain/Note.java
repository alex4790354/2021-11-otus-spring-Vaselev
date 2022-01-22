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
