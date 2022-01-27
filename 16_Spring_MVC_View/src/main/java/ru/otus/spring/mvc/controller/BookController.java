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
import ru.otus.spring.mvc.domain.Person;
import ru.otus.spring.mvc.repositories.PersonRepository;
import ru.otus.spring.mvc.services.BookService;

import javax.validation.Valid;
import java.util.List;



@Controller
public class PersonController {

    private final PersonRepository repository;
    private final BookService bookService;

    @Autowired
    public PersonController(PersonRepository repository, BookService bookService) {
        this.repository = repository;
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public String listBooks(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "books";
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
