package ru.otus.spring.noSql.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotBlank;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "COMMENT")
public class Note {

    @Id
    private String id;

    @NotBlank
    private String note;

    private String bookId;

    public Note(String note, String bookId) {
        this(null, note, bookId);
    }

}
