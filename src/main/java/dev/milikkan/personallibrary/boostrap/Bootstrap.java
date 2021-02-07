package dev.milikkan.personallibrary.boostrap;

import dev.milikkan.personallibrary.model.Author;
import dev.milikkan.personallibrary.model.Book;
import dev.milikkan.personallibrary.model.Publisher;
import dev.milikkan.personallibrary.repository.AuthorRepository;
import dev.milikkan.personallibrary.repository.BookRepository;
import dev.milikkan.personallibrary.repository.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    public Bootstrap(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    private void initData() {
        System.out.println("LOADING BOOKS...");

        Publisher addisonWesley = new Publisher("Addison-Wesley Professional", "");
        publisherRepository.save(addisonWesley);

        Book refactoring = new Book("Refactoring to Patterns", "", "", "0321213351",
                addisonWesley,
                "This book introduces the theory and practice of pattern-directed refactorings");

        Author joshua = new Author("Joshua Kerievsky", "A great author");
        Author mustafa = new Author("Mustafa Ilikkan", "Fake author");

        refactoring.getAuthors().add(joshua);

        refactoring.getAuthors().add(mustafa);

        authorRepository.save(joshua);
        authorRepository.save(mustafa);
        bookRepository.save(refactoring);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}
