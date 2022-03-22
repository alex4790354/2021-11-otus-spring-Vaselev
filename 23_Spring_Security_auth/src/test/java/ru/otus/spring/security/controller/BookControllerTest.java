package ru.otus.spring.security.controller;


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
import ru.otus.spring.security.domain.Author;
import ru.otus.spring.security.domain.Book;
import ru.otus.spring.security.domain.Genre;
import ru.otus.spring.security.dto.BookDto;
import ru.otus.spring.security.services.*;
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
    private static final Author AUTHOR = new Author(1L, "AuthorL");
    private static final Genre GENRE = new Genre(1L, "Genre1");
    private static final Book BOOK = new Book(1L, AUTHOR, GENRE, "Title1");
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
    private ConversionService conversionService;

    @DisplayName("для авторизованного пользователя возвращать успешный ответ при получении списка книг")
    @WithMockUser
    @Test
    public void finAll() throws Exception {
        given(bookService.findAll()).willReturn(List.of(BOOK));

        this.mockMvc.perform(get("/books")).andDo(print()).andExpect(status().isOk());
    }

    @DisplayName("для авторизованного пользователя возвращать успешный ответ при получении информации о книге")
    @WithMockUser
    @Test
    void edit() throws Exception {
        given(bookService.findById(anyLong())).willReturn(BOOK);
        given(conversionService.fromDomain(BOOK)).willReturn(BOOK_DTO);

        this.mockMvc.perform(get("/books/editBook?id=" + BOOK
                .getId())).andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("для авторизованного пользователя возвращать ответ 302 (REDIRECTION) при сохранении книги")
    @Test
    void save() throws Exception {
        given(bookService.saveBook(any(Book.class))).willReturn(BOOK);

        this.mockMvc.perform(post("/books/editBook")
                .with(csrf())
                .param("id", "1")
                .param("title", "Title1"))
                .andExpect(status().isFound());
    }

    @DisplayName("для авторизованного пользователя возвращать ответ 302 (REDIRECTION) при удалении книги")
    @Test
    void delete() throws Exception {
        doNothing().when(bookService).deleteBook(anyLong());

        this.mockMvc.perform(post("/books/delete")
                        .with(csrf())
                        .param("id", "1"))
                .andExpect(status().isFound());
    }

    @DisplayName("для неавторизованного пользователя возвращать ответ 302 (REDIRECTION) при получении списка книг")
    @Test
    public void finAllForbidden() throws Exception {
        given(bookService.findAll()).willReturn(List.of(BOOK));

        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isFound());
    }

    @DisplayName("для неавторизованного пользователя возвращать ответ 302 (REDIRECTION) при получении информации о книге")
    @Test
    void editForbidden() throws Exception {
        given(bookService.findById(anyLong())).willReturn(BOOK);
        this.mockMvc.perform(get("/books/edit?id=" + BOOK.getId())).andDo(print()).andExpect(status().isFound());
    }

    @DisplayName("для неавторизованного пользователя возвращать ответ 403 (FORBIDDEN) при сохранении книги")
    @Test
    void saveForbidden() throws Exception {
        given(bookService.saveBook(any(Book.class))).willReturn(BOOK);

        this.mockMvc.perform(post("/books/edit")
                        .param("id", "1")
                        .param("name", "book1"))
                .andExpect(status().isForbidden());
    }

    @DisplayName("для неавторизованного пользователя возвращать ответ 403 (FORBIDDEN) при удалении книги")
    @Test
    void deleteForbidden() throws Exception {
        doNothing().when(bookService).deleteBook(anyLong());

        this.mockMvc.perform(post("/books/delete")
                        .param("id", "1"))
                .andExpect(status().isForbidden());
    }
}