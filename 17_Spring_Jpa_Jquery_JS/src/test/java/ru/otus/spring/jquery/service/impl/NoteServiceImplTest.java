package ru.otus.spring.jquery.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.spring.jquery.domain.Author;
import ru.otus.spring.jquery.domain.Book;
import ru.otus.spring.jquery.domain.Genre;
import ru.otus.spring.jquery.domain.Note;
import ru.otus.spring.jquery.repository.NoteRepository;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("ORM JPA Genres repository testing.")
@DataJpaTest
class NoteServiceImplTest {

    private final static int EXPECTED_NOTES_COUNT = 27;
    private final static long NOTE_ONE_ID = 1L;
    private final static String NOTE_ONE_CONTEXT = "Note-01.1 - Мастер";
    private final static String NOTE_ONE_CONTEXT_NEW = "Note-01.1 - Мастер - New";
    private final static Author AUTHOR_ONE = new Author(1, "Михаил Булгаков");
    private final static Genre GENRE_ONE = new Genre(1, "Роман");
    private final static String BOOK_ONE_NAME = "Мастер и Маргарита";
    private final static Book BOOK_ONE = new Book(1, AUTHOR_ONE, GENRE_ONE, BOOK_ONE_NAME);

    @Autowired
    private NoteRepository noteRepository;


    @DisplayName("Should get correct Note by ID")
    @Test
    void shouldGetCorrectGenre() {
        Note genre = noteRepository.getById(NOTE_ONE_ID);
        assertEquals(NOTE_ONE_ID, genre.getId());
        assertEquals(NOTE_ONE_CONTEXT, genre.getNote());
    }


    @DisplayName("Should be able to insert a Genre-1 after deletions")
    @Test
    void shouldAddNewGenre() {
        Note note = new Note(0L, BOOK_ONE, NOTE_ONE_CONTEXT_NEW);
        Note savedNote = noteRepository.save(note);
        assertThat(savedNote.getId()).isGreaterThan(0);
        assertEquals(NOTE_ONE_CONTEXT_NEW, savedNote.getNote());
    }

}