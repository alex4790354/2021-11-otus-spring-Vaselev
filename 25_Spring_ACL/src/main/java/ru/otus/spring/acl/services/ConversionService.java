package ru.otus.spring.acl.services;

import ru.otus.spring.acl.domain.Book;
import ru.otus.spring.acl.dto.BookDto;
import java.util.List;


public interface ConversionService {

    List<BookDto> fromDomain(List<Book> books);

    BookDto fromDomain(Book book);

    List<Book> fromDto(List<BookDto> booksDto);

    Book fromDto(BookDto bookDto);

}
