package dev.milikkan.personallibrary.controller;

import dev.milikkan.personallibrary.entity.Author;
import dev.milikkan.personallibrary.entity.Book;
import dev.milikkan.personallibrary.repository.AuthorRepository;
import dev.milikkan.personallibrary.repository.BookRepository;
import dev.milikkan.personallibrary.repository.PublisherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class BookController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    public BookController(BookRepository bookRepository,
                          AuthorRepository authorRepository,
                          PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "index";
    }

    @ModelAttribute(name = "newBook")
    public Book book() {
        return new Book();
    }

    @GetMapping("/books/new")
    public String newBookForm(Model model) {
        model.addAttribute("allAuthors", authorRepository.findAll());
        model.addAttribute("publishers", publisherRepository.findAll());
        return "new-book";
    }

    @PostMapping("/books/new")
    public String saveBook(Book newBook) {
        // save author first
        bookRepository.save(newBook);
        return "redirect:/";
    }
}
