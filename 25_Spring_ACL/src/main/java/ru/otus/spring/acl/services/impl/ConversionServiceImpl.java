package ru.otus.spring.acl.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.acl.domain.Book;
import ru.otus.spring.acl.dto.BookDto;
import ru.otus.spring.acl.repositories.NoteRepository;
import ru.otus.spring.acl.services.ConversionService;
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
