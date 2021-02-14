package dev.milikkan.personallibrary.controller;

import dev.milikkan.personallibrary.entity.Author;
import dev.milikkan.personallibrary.entity.Book;
import dev.milikkan.personallibrary.entity.Publisher;
import dev.milikkan.personallibrary.exception.BookNotFoundException;
import dev.milikkan.personallibrary.service.AuthorService;
import dev.milikkan.personallibrary.service.BookService;
import dev.milikkan.personallibrary.service.PublisherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final PublisherService publisherService;

    @ModelAttribute(name = "newBook")
    public Book newBook() {
        return new Book();
    }

    @ModelAttribute(name = "allBooks")
    public List<Book> allBooks() {
        return bookService.findAll();
    }

    @ModelAttribute(name = "allAuthors")
    public List<Author> allAuthors() {
        return authorService.findAll();
    }

    @ModelAttribute(name = "allPublishers")
    public List<Publisher> allPublishers() {
        return publisherService.findAll();
    }

    @GetMapping({"/", ""})
    public String listAllBooks(
            @ModelAttribute(name = "allBooks") List<Book> allBooks)
    {
        return "book/list-books";
    }

    @GetMapping("/new")
    public String newBookForm(
            @ModelAttribute(name = "allAuthors") List<Author> allAuthors,
            @ModelAttribute(name = "allPublishers") List<Publisher> allPublishers,
            Model model)
    {
        model.addAttribute("authorListForNewBook",
                new ArrayList<Author>().add(new Author()));

        return "book/new-book";
    }

    @PostMapping("/new")
    public String saveBook(Book newBook) {
        var incomingAuthorList = newBook.getAuthors();
        newBook.setAuthors(
                authorService.sanitizeAuthorList(incomingAuthorList, false)
        );

        var incomingPublisher = newBook.getPublisher();
        publisherService.sanitizePublisher(incomingPublisher)
                .ifPresent(newBook::setPublisher);

        bookService.save(newBook);
        return "redirect:/books";
    }

    @GetMapping("/{bookId}/update")
    public String updateBookForm(@PathVariable Long bookId, Model model) {

        Book bookForUpdate = bookService.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        model.addAttribute("bookForUpdate", bookForUpdate);
        return "book/update-book";
    }

    @PostMapping("/{bookId}/update")
    public String updateBook(
            @PathVariable Long bookId,
            @ModelAttribute(name = "bookForUpdate") Book bookForUpdate)
    {
        Book oldBook = bookService.findById(bookId).get();
        oldBook.setTitle(bookForUpdate.getTitle());
        oldBook.setSubtitle(bookForUpdate.getSubtitle());
        oldBook.setSeries(bookForUpdate.getSeries());
        oldBook.setIsbn(bookForUpdate.getIsbn());
        oldBook.setExplanation(bookForUpdate.getExplanation());

        // handle incoming author list
        var incomingAuthorList = bookForUpdate.getAuthors();
        oldBook.setAuthors(
                authorService.sanitizeAuthorList(incomingAuthorList, true)
        );

        // handle incoming publisher
        var incomingPublisher = bookForUpdate.getPublisher();
        var found = publisherService.sanitizePublisher(incomingPublisher);
        if (found.isPresent()) {
            oldBook.setPublisher(found.get());
        } else {
            oldBook.setPublisher(incomingPublisher);
        }

        bookService.save(oldBook);
        return "redirect:/books";
    }

    @GetMapping("/{bookId}")
    public String showBookDetails(@PathVariable Long bookId, Model model) {
        Book book = bookService.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
        model.addAttribute("book", book);

        return "book/book-details";
    }

    @GetMapping("/{bookId}/delete")
    public String deleteBook(@PathVariable Long bookId) {
        bookService.deleteById(bookId);
        return "redirect:/books";
    }
}
