package ru.otus.spring.noSql.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "GENRE")
public class Genre {

    @Id
    private String id;

    @NotNull
    private String name;

    public Genre(String name) {
        this.name = name;
    }

}
