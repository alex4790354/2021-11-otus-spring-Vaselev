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
import ru.otus.spring.acl.domain.*;
import ru.otus.spring.acl.dto.BookDto;
import ru.otus.spring.acl.services.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DisplayName("Контроллер для работы с комментариями должен ")
@ExtendWith(SpringExtension.class)
@WithMockUser
@WebMvcTest(NotesController.class)
class NotesControllerTest {

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
    private NoteService noteService;


    ///
    @MockBean
    private AuthorService authorService;

    @MockBean
    private GenreService genreService;

    @MockBean
    private ConversionService conversionService;


    @DisplayName("для авторизованного сохранять комментарий")
    @WithMockUser(username = "user", authorities = {"ROLE_USER"})
    @Test
    void saveForUser() throws Exception {
        given(noteService.save(any(Note.class))).willReturn(NOTE);

        this.mockMvc.perform(post("/notes/saveBookNote")
                        .with(csrf())
                        .param("noteId", "1")
                        .param("note", "Note1")
                        .param("id", "1")
                )
                .andExpect(status().isFound());
    }

    @DisplayName("для неавторизованного не сохранять комментарий")
    @Test
    void saveNotAuth() throws Exception {
        given(noteService.save(any(Note.class))).willReturn(NOTE);

        this.mockMvc.perform(post("/notes/saveBookNote")
                        .param("text", "comment1")
                        .param("book.id", "1"))
                .andExpect(status().isForbidden());
    }

    @DisplayName("для авторизованного удалять комментарий")
    @WithMockUser(username = "user", authorities = {"ROLE_USER"})
    @Test
    void delete() throws Exception {
        doNothing().when(noteService).delete(anyLong());

        this.mockMvc.perform(post("/notes/delete")
                        .with(csrf())
                        .param("id", "1")
                        .param("bookId", "1"))
                .andExpect(status().isFound());
    }

    @DisplayName("для неавторизованного не удалять комментарий")
    @Test
    void deleteNotAuth() throws Exception {
        doNothing().when(noteService).delete(anyLong());

        this.mockMvc.perform(post("/notes/delete")
                        .param("id", "1")
                        .param("bookId", "1"))
                .andExpect(status().isForbidden());
    }

}