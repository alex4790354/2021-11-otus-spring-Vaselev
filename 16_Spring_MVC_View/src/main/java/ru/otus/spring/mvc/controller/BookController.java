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
import ru.otus.spring.mvc.domain.Author;
import ru.otus.spring.mvc.domain.Book;
import ru.otus.spring.mvc.domain.Genre;
import ru.otus.spring.mvc.domain.Person;
import ru.otus.spring.mvc.repositories.PersonRepository;
import ru.otus.spring.mvc.services.AuthorService;
import ru.otus.spring.mvc.services.BookService;
import ru.otus.spring.mvc.services.GenreService;
import ru.otus.spring.mvc.services.NoteService;

import javax.validation.Valid;
import java.util.List;



@Controller
public class BookController {

    private final PersonRepository repository;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookService bookService;
    private final NoteService noteService;

    @Autowired
    public BookController(PersonRepository repository,
                          AuthorService authorService,
                          GenreService genreService,
                          BookService bookService,
                          NoteService noteService) {
        this.repository = repository;
        this.authorService = authorService;
        this.genreService = genreService;
        this.bookService = bookService;
        this.noteService = noteService;
    }

    @GetMapping("/books")
    public String listBooks(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        model.addAttribute("notes", books);
        return "books";
    }

    @GetMapping("/editBook")
    public String editBook(@RequestParam("id") long id, Model model) {
        Book book = bookService.findById(id);
        List<Author> authors = authorService.findAll();
        List<Genre> genres = genreService.findAll();
        model.addAttribute("book", book);
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
        return "editBook";
    }

    @Validated
    @PostMapping("/editBook")
    public String saveBook(@Valid @ModelAttribute("book") Book book,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editBook";
        }
        bookService.saveBook(book);
        return "redirect:/books";
    }





    @GetMapping("/")
    public String listPage(Model model) {
        List<Person> persons = repository.findAll();
        model.addAttribute("persons", persons);
        return "list";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") long id, Model model) {
        Person person = repository.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("person", person);
        return "edit";
    }

    @Validated
    @PostMapping("/edit")
    public String savePerson(@Valid @ModelAttribute("person") Person person,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        repository.save(person);
        return "redirect:/";
    }
}
