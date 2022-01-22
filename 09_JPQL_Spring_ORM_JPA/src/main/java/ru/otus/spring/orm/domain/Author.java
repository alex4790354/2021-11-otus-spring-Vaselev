package ru.otus.spring.orm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
//@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Author")
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;


    /*@OneToMany(mappedBy = "author",
            cascade = CascadeType.ALL,
            fetch=FetchType.LAZY,
            orphanRemoval = false)
    @ToString.Exclude
    private List<Book> books = new ArrayList<Book>();*/

    public Author(long id, String name) {
        this.id = id;
        this.name = name;
        //this.books = new ArrayList<>();
    }
}
