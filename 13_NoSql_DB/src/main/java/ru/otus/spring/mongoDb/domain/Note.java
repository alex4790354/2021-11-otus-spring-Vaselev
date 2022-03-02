package ru.otus.spring.mongoDb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "comments")
public class Note {

    public Note(String bookId, String content) {
        this.bookId = bookId;
        this.content = content;
    }

    @Id
    private String id;
    private String bookId;
    private String content;

}
