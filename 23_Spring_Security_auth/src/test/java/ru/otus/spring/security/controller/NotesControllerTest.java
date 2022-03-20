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
import ru.otus.spring.security.domain.*;
import ru.otus.spring.security.dto.NoteDto;
import ru.otus.spring.security.services.*;

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
    private static final Note NOTE = new Note(1L, BOOK, "Note1");
    private static final NoteDto NOTE_DTO = new NoteDto(1L, BOOK, "Note1");

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private BookService bookService;

    @MockBean
    private NoteService noteService;


    @DisplayName("для авторизованного пользователя возвращать ответ 302 (REDIRECTION) при сохранении комментария")
    @WithMockUser
    @Test
    void save() throws Exception {
        given(noteService.save(any(Note.class))).willReturn(NOTE);

        this.mockMvc.perform(post("/notes/editBookNote")
                                .with(csrf())
                                .param("noteId", "1")
                                .param("note", "Note1")
                                .param("id", "1")
                            ).andExpect(status().isFound());
    }

    @DisplayName("для авторизованного пользователя возвращать ответ 302 (REDIRECTION) при удалении комментария")
    @WithMockUser
    @Test
    void delete() throws Exception {
        doNothing().when(noteService).delete(anyLong());

        this.mockMvc.perform(post("/notes/delete")
                        .with(csrf())
                        .param("id", "1")
                        .param("bookId", "1")
                        )
                .andExpect(status().isFound());
    }


    @DisplayName("для неавторизованного пользователя возвращать ответ 403 (FORBIDDEN) при сохранении комментария")
    @Test
    void saveForbidden() throws Exception {
        given(noteService.save(any(Note.class))).willReturn(NOTE);
        this.mockMvc.perform(post("/notes/editBookNote")
                                .param("id", "1")
                                .param("note", "Note1")
                            )
                    .andExpect(status().isForbidden());
    }

    @DisplayName("для неавторизованного пользователя возвращать ответ 403 (FORBIDDEN) при удалении комментария")
    @Test
    void deleteForbidden() throws Exception {
        doNothing().when(noteService).delete(anyLong());

        this.mockMvc.perform(post("/notes/delete")
                        .param("id", "1")
                        .param("bookId", "1"))
                .andExpect(status().isForbidden());
    }

}