package ru.otus.spring.orm.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "reviewEntity")
@Table(name = "review")
public class Review {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "review")
    private String review;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "book_id")
    private Book book;

}
