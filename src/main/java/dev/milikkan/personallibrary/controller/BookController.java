package dev.milikkan.personallibrary.controller;

import dev.milikkan.personallibrary.entity.Author;
import dev.milikkan.personallibrary.entity.Book;
import dev.milikkan.personallibrary.entity.Publisher;
import dev.milikkan.personallibrary.exception.BookNotFoundException;
import dev.milikkan.personallibrary.repository.AuthorRepository;
import dev.milikkan.personallibrary.repository.BookRepository;
import dev.milikkan.personallibrary.repository.PublisherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.stream.Collectors;

@AllArgsConstructor
@Controller
public class BookController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

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
        model.addAttribute("allPublishers", publisherRepository.findAll());
        model.addAttribute("authorListForNewBook", new ArrayList<Author>().add(new Author()));
        return "new-book";
    }

    @PostMapping("/books/new")
    public String saveBook(Book newBook) {
        // handle incoming author list
        var allAuthors = authorRepository.findAll();
        var incomingAuthorList = newBook.getAuthors();
        var checkedAuthorList = new ArrayList<Author>();
        // if name and explanation completely match -> use the one from db
        var allAuthorNames = allAuthors.stream()
                .map(Author::getFullName)
                .collect(Collectors.toList());

        var allAuthorExplanations = allAuthors.stream()
                .map(Author::getExplanation)
                .collect(Collectors.toList());

        for (Author author : incomingAuthorList) {
            if (allAuthorNames.contains(author.getFullName())
                    && (allAuthorExplanations.contains(author.getExplanation())))
            {
                var found = allAuthors.stream()
                        .filter(a ->
                                a.getFullName().equals(author.getFullName()) &&
                                a.getExplanation().equals(author.getExplanation()))
                        .findFirst();
                found.ifPresent(checkedAuthorList::add);
            } else {
                checkedAuthorList.add(author);
            }
        }
        newBook.setAuthors(checkedAuthorList);

        // handle incoming publisher
        var incomingPublisher = newBook.getPublisher();
        var allPublishers = publisherRepository.findAll();
        var allPublisherNames = allPublishers.stream()
                .map(Publisher::getName)
                .collect(Collectors.toList());
        var allPublisherExplanations = allPublishers.stream()
                .map(Publisher::getExplanation)
                .collect(Collectors.toList());

        if (allPublisherNames.contains(incomingPublisher.getName())
                && (allPublisherExplanations.contains(incomingPublisher.getExplanation())))
        {
            var found = allPublishers.stream()
                    .filter(p ->
                            p.getName().equals(incomingPublisher.getName()) &&
                                    p.getExplanation().equals(incomingPublisher.getExplanation()))
                    .findFirst();
            found.ifPresent(newBook::setPublisher);
        }

        bookRepository.save(newBook);
        return "redirect:/";
    }

    @GetMapping("/books/{bookId}/update")
    public String updateBookForm(@PathVariable Long bookId, Model model) {
        model.addAttribute("allAuthors", authorRepository.findAll());
        model.addAttribute("allPublishers", publisherRepository.findAll());

        Book bookForUpdate = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        model.addAttribute("bookForUpdate", bookForUpdate);
        model.addAttribute("bookId", bookForUpdate.getId());
        model.addAttribute("existingAuthors", bookForUpdate.getAuthors());
        return "update-book";
    }

    @PostMapping("/books/{bookId}/update")
    public String updateBook(@ModelAttribute(name = "bookForUpdate") Book bookForUpdate,
                             @PathVariable Long bookId)
    {
        Book oldBook = bookRepository.findById(bookId).get();
        oldBook.setTitle(bookForUpdate.getTitle());
        oldBook.setSubtitle(bookForUpdate.getSubtitle());
        oldBook.setSeries(bookForUpdate.getSeries());
        oldBook.setIsbn(bookForUpdate.getIsbn());
        oldBook.setExplanation(bookForUpdate.getExplanation());

        // handle incoming author list
        var allAuthors = authorRepository.findAll();
        var incomingAuthorList = bookForUpdate.getAuthors();
        var checkedAuthorList = new ArrayList<Author>();
        // if name and explanation completely match -> use the one from db
        var allAuthorNames = allAuthors.stream()
                .map(Author::getFullName)
                .collect(Collectors.toList());

        var allAuthorExplanations = allAuthors.stream()
                .map(Author::getExplanation)
                .collect(Collectors.toList());

        for (Author author : incomingAuthorList) {
            if (allAuthorNames.contains(author.getFullName())
                    && (allAuthorExplanations.contains(author.getExplanation())))
            {
                var found = allAuthors.stream()
                        .filter(a ->
                                a.getFullName().equals(author.getFullName()) &&
                                        a.getExplanation().equals(author.getExplanation()))
                        .findFirst();
                found.ifPresent(checkedAuthorList::add);
            } else {
                authorRepository.save(author);
                checkedAuthorList.add(author);
            }
        }

        oldBook.setAuthors(checkedAuthorList);
        oldBook.getAuthors().forEach(a -> System.out.println(a.getFullName()));
        // handle incoming publisher
        var incomingPublisher = bookForUpdate.getPublisher();
        var allPublishers = publisherRepository.findAll();
        var allPublisherNames = allPublishers.stream()
                .map(Publisher::getName)
                .collect(Collectors.toList());
        var allPublisherExplanations = allPublishers.stream()
                .map(Publisher::getExplanation)
                .collect(Collectors.toList());

        if (allPublisherNames.contains(incomingPublisher.getName())
                && (allPublisherExplanations.contains(incomingPublisher.getExplanation())))
        {
            var found = allPublishers.stream()
                    .filter(p ->
                            p.getName().equals(incomingPublisher.getName()) &&
                                    p.getExplanation().equals(incomingPublisher.getExplanation()))
                    .findFirst();
            found.ifPresent(oldBook::setPublisher);
        } else {
            oldBook.setPublisher(bookForUpdate.getPublisher());
        }

        bookRepository.save(oldBook);
        return "redirect:/";
    }

    @GetMapping("/books/{bookId}")
    public String showBookDetails(@PathVariable Long bookId, Model model) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
        model.addAttribute("book", book);

        return "book-details";
    }

    @GetMapping("/books/{bookId}/delete")
    public String deleteBook(@PathVariable Long bookId) {

        bookRepository.deleteById(bookId);

        return "redirect:/";
    }

}
