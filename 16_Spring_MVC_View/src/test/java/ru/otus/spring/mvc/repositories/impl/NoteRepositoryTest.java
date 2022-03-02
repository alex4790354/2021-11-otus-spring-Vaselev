package ru.otus.spring.mvc.repositories.impl;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.spring.mvc.domain.Note;
import ru.otus.spring.mvc.repositories.NoteRepository;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("ORM JPA Genres repository testing.")
@DataJpaTest
class NoteRepositoryTest {

    private final static int EXPECTED_NOTES_COUNT = 27;

    @Autowired
    private NoteRepository noteRepository;


    @DisplayName("Should find all Notes")
    @Test
    void ShouldGetAllNotes() {
        List<Note> notes = noteRepository.findAll();
        assertThat(notes).isNotNull().hasSize(EXPECTED_NOTES_COUNT)
                .allMatch(s -> s.getId() > 0)
                .allMatch(s -> !s.getNote().equals(""));
    }

}