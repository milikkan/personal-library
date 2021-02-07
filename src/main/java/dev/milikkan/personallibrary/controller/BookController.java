package dev.milikkan.personallibrary.controller;

import dev.milikkan.personallibrary.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "index";
    }
}
