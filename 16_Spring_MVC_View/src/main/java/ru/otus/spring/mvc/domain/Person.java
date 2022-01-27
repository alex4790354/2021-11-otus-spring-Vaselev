package ru.otus.spring.mvc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotBlank(message = "{name-field-should-not-be-blank}")
    @Size(min = 2, max = 10, message = "{name-field-should-has-expected-size}")
    private String name;


    public Person(String name) {
        this.name = name;
    }



}
