package ru.otus.spring.noSql.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Document(collection = "AUTHOR")
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @Id
    private String id;

    @NotNull
    private String name;

    public Author(String name) {
        this.name = name;
    }

}
