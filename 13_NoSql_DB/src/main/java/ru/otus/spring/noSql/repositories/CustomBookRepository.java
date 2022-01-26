package ru.otus.spring.noSql.repositories;

public interface CustomBookRepository {

    void removeAllBooksComments(String bookId);

}
