package ru.otus.spring.mvc.services;

import ru.otus.spring.mvc.domain.Book;
import ru.otus.spring.mvc.dto.BookDto;
import java.util.List;


public interface ConversionService {

    List<BookDto> fromDomain(List<Book> books);

    BookDto fromDomain(Book book);

    List<Book> fromDto(List<BookDto> booksDto);

    Book fromDto(BookDto bookDto);

}
