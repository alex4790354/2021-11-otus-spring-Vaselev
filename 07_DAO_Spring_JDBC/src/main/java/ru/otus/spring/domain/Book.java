package ru.otus.spring.domain;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Book {
    private final int id;
    private final int authorId;
    private final int genreId;
    private final String name;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Book book2 = (Book) obj;
        if (this.id == book2.id &&
            this.authorId == book2.authorId &&
            this.genreId == book2.genreId &&
            this.name.equals(book2.getName())) {
            return true;
        }
        else {
            return false;
        }
    }

    public int getId() {
        return id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public int getGenreId() {
        return genreId;
    }

    public String getName() {
        return name;
    }
}
