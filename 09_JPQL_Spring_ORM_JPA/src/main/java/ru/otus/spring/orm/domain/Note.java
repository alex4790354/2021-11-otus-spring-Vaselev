package ru.otus.spring.orm.domain;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Note")
@Table(name = "Note")
@EqualsAndHashCode(exclude = "book")
public class Note {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(targetEntity = Book.class)
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    private Book book;

    @NotBlank
    @Column(name = "note")
    private String note;

    public Note(Book book, String note) {
        this.book = book;
        this.note = note;
    }

}
