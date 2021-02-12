package dev.milikkan.personallibrary.controller;

import dev.milikkan.personallibrary.entity.Author;
import dev.milikkan.personallibrary.entity.Book;
import dev.milikkan.personallibrary.entity.Publisher;
import dev.milikkan.personallibrary.repository.AuthorRepository;
import dev.milikkan.personallibrary.repository.BookRepository;
import dev.milikkan.personallibrary.repository.PublisherRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
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

}
