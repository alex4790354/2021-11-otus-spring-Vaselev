package ru.otus.spring.integration.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.spring.integration.util.Util;

@AllArgsConstructor
@Data
public class Student {

    private Long id;
    private String firstName;
    private String lastName;

    public Student(Long id) {
        this.id = id;
        this.firstName = Util.faker().name().firstName();
        this.lastName = Util.faker().name().lastName();
    }
}
