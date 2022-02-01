package ru.otus.spring.jquery.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentDto {

    private Long id;

    private String text;

    private Long bookId;
}
