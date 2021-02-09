package dev.milikkan.personallibrary.boostrap;

import dev.milikkan.personallibrary.entity.Author;
import dev.milikkan.personallibrary.entity.Book;
import dev.milikkan.personallibrary.entity.Publisher;
import dev.milikkan.personallibrary.repository.AuthorRepository;
import dev.milikkan.personallibrary.repository.BookRepository;
import dev.milikkan.personallibrary.repository.PublisherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@AllArgsConstructor
public class DataLoader {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    @PostConstruct
    public void loadData() {
        // publishers
        Publisher noStarch = new Publisher(
                1L,
                "No Starch Press",
                "Explanation"
                );
        publisherRepository.save(noStarch);

        Publisher adWes = new Publisher(
                2L,
                "Addison-Wesley Professional",
                "Explanation"
        );
        publisherRepository.save(adWes);

        // authors
        Author daniel = new Author(
                1L,
                "Daniel Zingaro",
                "explanation"
        );
        authorRepository.save(daniel);

        Author brianGoetz = new Author(
                2L,
                "Brian Goetz",
                "explanation"
        );
        authorRepository.save(brianGoetz);

        Author timPeierls = new Author(
                3L,
                "Tim Peierls",
                "explanation"
        );
        authorRepository.save(timPeierls);

        Author joshuaBloch = new Author(
                4L,
                "Joshua Bloch",
                "explanation"
        );
        authorRepository.save(joshuaBloch);

        Author josephBowbeer = new Author(
                5L,
                "Joseph Bowbeer",
                "explanation"
        );
        authorRepository.save(josephBowbeer);

        Author davidHolmes = new Author(
                6L,
                "David Holmes",
                "explanation"
        );
        authorRepository.save(davidHolmes);

        Author dougLea = new Author(
                7L,
                "Doug Lea",
                "explanation"
        );
        authorRepository.save(dougLea);

        Author ericEvans = new Author(
                8L,
                "Eric Evans",
                "explanation"
        );
        authorRepository.save(ericEvans);

        // books
        Book algo = new Book(
                1L,
                "Algorithmic Thinking",
                "A Problem-Based Introduction",
                "",
                "123456789",
                noStarch,
                "Explanation"
        );
        algo.getAuthors().add(daniel);
        bookRepository.save(algo);

        Book javaConcurrency = new Book(
                2L,
                "Java Concurrency in Practice",
                "",
                "",
                "123456789",
                adWes,
                "Explanation"
        );
        javaConcurrency.getAuthors().add(brianGoetz);
        javaConcurrency.getAuthors().add(timPeierls);
        javaConcurrency.getAuthors().add(joshuaBloch);
        javaConcurrency.getAuthors().add(josephBowbeer);
        javaConcurrency.getAuthors().add(davidHolmes);
        javaConcurrency.getAuthors().add(dougLea);
        bookRepository.save(javaConcurrency);

        Book ddd = new Book(
                3L,
                "Domain-Driven Design",
                "Tackling Complexity in the Heart of Software",
                "",
                "123456789",
                adWes,
                "Explanation"
        );
        ddd.getAuthors().add(ericEvans);
        bookRepository.save(ddd);

    }
}
