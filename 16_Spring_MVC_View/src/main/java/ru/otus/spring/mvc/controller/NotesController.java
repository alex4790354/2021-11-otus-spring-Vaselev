package ru.otus.spring.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.mvc.domain.Book;
import ru.otus.spring.mvc.domain.Note;
import ru.otus.spring.mvc.dto.NoteDto;
import ru.otus.spring.mvc.services.*;
import javax.validation.Valid;
import java.util.List;


@Controller
public class NotesController {

    private final BookService bookService;
    private final NoteService noteService;

    @Autowired
    public NotesController(BookService bookService,
                           NoteService noteService) {
        this.bookService = bookService;
        this.noteService = noteService;
    }

    @GetMapping("/notes/bookNotes")
    public String listBooks(@RequestParam("bookId") long bookId,
                            Model model) {
        Book book = bookService.findById(bookId);
        List<Note> notes = noteService.findByBookId(bookId);
        model.addAttribute("book", book);
        model.addAttribute("notes", notes);
        return "bookNotes";
    }


    @GetMapping("/notes/editBookNote")
    public String editBookNote(@RequestParam("noteId") long noteId,
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
    @PostMapping("/notes/editBookNote")
    public String save(@Valid @ModelAttribute("noteDto") NoteDto noteDto,
                       BindingResult bindingResult,
                       Model model) {

        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.toString());
            model.addAttribute("noteDto", noteDto);
            return "editBookNote";
        }

        noteService.save(noteDto.toDomainObject());
        if (noteDto != null && noteDto.getBook() != null) {
            return "redirect:/notes/bookNotes?bookId=" + noteDto.getBook().getId();
        } else {
            return "redirect:/books";
        }
    }

    @PostMapping("/notes/delete")
    public String delete(@RequestParam("id") Long id
            ,@RequestParam("bookId") Long bookId ) {

        noteService.delete(id);
        return "redirect:/notes/bookNotes?bookId=" + bookId;
        //return "redirect:/books";
    }

}
