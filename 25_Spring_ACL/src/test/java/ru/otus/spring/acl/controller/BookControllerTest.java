package ru.otus.spring.acl.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.acl.domain.Author;
import ru.otus.spring.acl.domain.Book;
import ru.otus.spring.acl.domain.Genre;
import ru.otus.spring.acl.domain.Note;
import ru.otus.spring.acl.dto.BookDto;
import ru.otus.spring.acl.services.*;

import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DisplayName("Контроллер для работы с книгами должен ")
@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
class BookControllerTest {

    private static final Author AUTHOR = new Author(1L, "Author1");
    private static final Genre GENRE = new Genre(1L, "Genre1");
    private static final Book BOOK = new Book(1L, AUTHOR, GENRE, "Book1");
    private static final Note NOTE = new Note(1L, BOOK, "Comment1");
    private static final BookDto BOOK_DTO = new BookDto(1L, AUTHOR, GENRE, "Title1", 0);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private BookService bookService;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private GenreService genreService;

    @MockBean
    private NoteService noteService;

    @MockBean
    private ConversionService conversionService;

    @DisplayName("для всех возвращать список книг")
    @Test
    public void finAll() throws Exception {
        given(bookService.findAll()).willReturn(List.of(BOOK));

        this.mockMvc.perform(get("/books")).andDo(print()).andExpect(status().isOk());
    }

    @DisplayName("для всех возвращать информацию о книге")
    @Test
    void edit() throws Exception {
        given(bookService.findById(anyLong())).willReturn(BOOK);
        given(conversionService.fromDomain(BOOK)).willReturn(BOOK_DTO);

        this.mockMvc.perform(get("/books/editBook?id=" + BOOK.getId())).andDo(print()).andExpect(status().isOk());
    }

    @DisplayName("для роли ADMIN сохранять книгу")
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    @Test
    void saveForAdmin() throws Exception {
        given(bookService.saveBook(any(Book.class))).willReturn(BOOK);

        this.mockMvc.perform(post("/books/saveBook")
                        .with(csrf())
                        .param("id", "1")
                        .param("name", "book1"))
                .andExpect(status().isOk());
    }

    @DisplayName("для роли USER не сохранять книгу")
    @WithMockUser(username = "user", authorities = {"ROLE_USER"})
    @Test
    void saveForUser() throws Exception {
        given(bookService.saveBook(any(Book.class))).willReturn(BOOK);

        this.mockMvc.perform(post("/books/save")
                        .param("id", "1")
                        .param("name", "book1"))
                .andExpect(status().isForbidden());
    }

    @DisplayName("для неавторизованного не сохранять книгу")
    @Test
    void saveNotAuth() throws Exception {
        given(bookService.saveBook(any(Book.class))).willReturn(BOOK);

        this.mockMvc.perform(post("/books/save")
                        .param("id", "1")
                        .param("name", "book1"))
                .andExpect(status().isForbidden());
    }

    @DisplayName("для роли ADMIN удалять книгу")
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    @Test
    void deleteForAdmin() throws Exception {
        doNothing().when(bookService).deleteBook(anyLong());

        this.mockMvc.perform(post("/books/delete")
                        .with(csrf())
                        .param("id", "1"))
                .andExpect(status().isFound());
    }

    @DisplayName("для роли USER не удалять книгу")
    @WithMockUser(username = "user", authorities = {"ROLE_USER"})
    @Test
    void deleteForUser() throws Exception {
        doNothing().when(bookService).deleteBook(anyLong());

        this.mockMvc.perform(post("/books/delete")
                        .param("id", "1"))
                .andExpect(status().isForbidden());
    }

    @DisplayName("для неавторизованного не удалять книгу")
    @Test
    void deleteNotAuth() throws Exception {
        doNothing().when(bookService).deleteBook(anyLong());

        this.mockMvc.perform(post("/books/delete")
                        .param("id", "1"))
                .andExpect(status().isForbidden());
    }

}