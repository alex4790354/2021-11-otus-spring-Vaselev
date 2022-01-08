package ru.otus.spring.jdbc.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;


@Data
@RequiredArgsConstructor
@Table(name = "genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private final long id;

    @Column(name = "name")
    private final String name;

}
