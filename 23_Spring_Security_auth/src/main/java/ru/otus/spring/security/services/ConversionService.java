package ru.otus.spring.security.services;

import ru.otus.spring.security.domain.Book;
import ru.otus.spring.security.dto.BookDto;
import java.util.List;


public interface ConversionService {

    List<BookDto> fromDomain(List<Book> books);

    BookDto fromDomain(Book book);

    List<Book> fromDto(List<BookDto> booksDto);

    Book fromDto(BookDto bookDto);

}
