package ru.otus.spring.mvc.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.mvc.domain.Book;
import ru.otus.spring.mvc.dto.BookDto;
import ru.otus.spring.mvc.repositories.NoteRepository;
import ru.otus.spring.mvc.services.ConversionService;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class ConversionServiceImpl implements ConversionService {

    private final NoteRepository noteRepository;

    @Override
    public List<BookDto> fromDomain(List<Book> books) {
        List<BookDto> booksDto = new ArrayList<>();
        for (Book book : books) {
            booksDto.add(fromDomain(book));
        }
        return booksDto;
    }

    @Override
    public BookDto fromDomain(Book book) {
        long count = noteRepository.countByBookId(book.getId());
        // TODO:  for 1000 comments would be 1001 DB requests. Have to change it.
        return new BookDto(book.getId(), book.getAuthor(), book.getGenre(), book.getTitle(), count);
    }

    @Override
    public List<Book> fromDto(List<BookDto> booksDto) {
        List<Book> books = new ArrayList<>();
        for (BookDto bookDto : booksDto) {
            books.add(fromDto(bookDto));
        }
        return books;
    }

    @Override
    public Book fromDto(BookDto bookDto) {
        return new Book(bookDto.getId(), bookDto.getAuthor(), bookDto.getGenre(), bookDto.getTitle());
    }
}
