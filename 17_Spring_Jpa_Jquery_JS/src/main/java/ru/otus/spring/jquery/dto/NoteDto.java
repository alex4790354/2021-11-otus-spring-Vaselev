package ru.otus.spring.jquery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class NoteDto {

    private Long id;

    private String note;

    private Long bookId;

}
