package ru.otus.spring.batch.domain.sql;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "genre")
@SecondaryTable(name = "mongo_genre", pkJoinColumns = @PrimaryKeyJoinColumn(name = "genre_id"))
public class Genre {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "mongo_id", table = "mongo_genre")
    private String mongoId;
}
