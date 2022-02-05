package ru.otus.spring.jquery.dto.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.otus.spring.jquery.domain.Note;
import ru.otus.spring.jquery.dto.NoteDto;
import static java.util.Objects.isNull;


@RequiredArgsConstructor
@Component
public class NoteMapper {

    private final ModelMapper mapper;

    public Note toEntity(NoteDto dto) {
        return isNull(dto) ? null : mapper.map(dto, Note.class);
    }

    public NoteDto toDto(Note entity) {
        return isNull(entity) ? null : mapper.map(entity, NoteDto.class);
    }
}
