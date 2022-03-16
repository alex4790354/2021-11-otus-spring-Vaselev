package ru.otus.spring.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.security.domain.Book;
import ru.otus.spring.security.domain.Note;
import ru.otus.spring.security.dto.NoteDto;
import ru.otus.spring.security.services.*;

import javax.validation.Valid;
import java.util.List;


@Controller
public class NotesController {

    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookService bookService;
    private final NoteService noteService;
    private final ConversionService conversionService;


    @Autowired
    public NotesController(AuthorService authorService,
                           GenreService genreService,
                           BookService bookService,
                           NoteService noteService,
                           ConversionService conversionService) {
        this.authorService = authorService;
        this.genreService = genreService;
        this.bookService = bookService;
        this.noteService = noteService;
        this.conversionService = conversionService;
    }

    @GetMapping("/bookNotes")
    public String listBooks(@RequestParam("bookId") long bookId, Model model) {
        Book book = bookService.findById(bookId);
        List<Note> notes = noteService.findByBookId(bookId);
        model.addAttribute("book", book);
        model.addAttribute("notes", notes);
        return "bookNotes";
    }


    @GetMapping("/editBookNote")
    public String editBook(@RequestParam("noteId") long noteId,
                           @RequestParam("bookId") long bookId,
                           Model model) {
        NoteDto noteDto = null;
        if (noteId > 0L) {
            Note note = noteService.findById(noteId);
            noteDto = NoteDto.fromDomainObject(note);
        } else {
            Book book = bookService.findById(bookId);
            noteDto = new NoteDto(0, book, "Write new review here");
        }
        model.addAttribute("noteDto", noteDto);
        return "editBookNote";
    }


    @Validated
    @PostMapping("/editBookNote")
    public String saveBook(@Valid @ModelAttribute("noteDto") NoteDto noteDto,
                           BindingResult bindingResult,
                           Model model) {

        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.toString());
            model.addAttribute("noteDto", noteDto);
            return "editBookNote";
        }

        noteService.save(noteDto.toDomainObject());
        //return "redirect:/bookNotes?bookId=" + noteDto.getBook().getId();
        return "redirect:/";
    }


}
