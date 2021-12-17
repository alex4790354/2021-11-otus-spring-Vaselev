package ru.otus.spring.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Data
public class Genre {
    private final int id;
    private final String name;

}
