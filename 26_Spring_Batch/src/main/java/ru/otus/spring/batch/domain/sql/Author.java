package ru.otus.spring.batch.domain.sql;

import lombok.*;

import javax.persistence.*;

import static java.lang.String.format;
import static javax.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "author")
@SecondaryTable(name = "mongo_author", pkJoinColumns = @PrimaryKeyJoinColumn(name = "author_id"))
public class Author {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "mongo_id", table = "mongo_author")
    private String mongoId;

    public String getFullName() {
        return format("%s %s", firstName, lastName);
    }
}
