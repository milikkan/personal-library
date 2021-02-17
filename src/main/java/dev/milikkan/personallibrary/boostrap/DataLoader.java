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
import java.util.ArrayList;
import java.util.Collections;

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
                "No Starch Press publishes the finest in geek entertainment — distinctive books on computing, such as bestsellers Steal This Computer Book, How Linux Works, Hacking: The Art of Exploitation, and The Unofficial LEGO Builder’s Guide, with a focus on open source/Linux, security, hacking, programming, and alternative operating systems."
                );
        publisherRepository.save(noStarch);

        Publisher adWes = new Publisher(
                2L,
                "Addison-Wesley Professional",
                "Addison-Wesley’s professional titles specialise in the area of computer programming. It is a leading publisher of high-quality and timely information for programmers, developers, engineers, and system administrators. Our mission is to provide educational materials about new technologies and new approaches to current technologies written by the leading authorities."
        );
        publisherRepository.save(adWes);

        // authors
        Author daniel = new Author(
                1L,
                "Daniel Zingaro",
                "Dr. Daniel Zingaro is an assistant teaching professor of computer science and award-winning teacher at the University of Toronto. His main area of research is computer science education research, where he studies how students learn (and sometimes don’t learn) computer science material."
        );
        authorRepository.save(daniel);

        Author brianGoetz = new Author(
                2L,
                "Brian Goetz",
                "Brian Goetz is the Java Language Architect at Oracle and was the specification lead for JSR-335 (Lambda Expressions for the Java Programming Language). He is the author of Java Concurrency in Practice and over 75 articles about Java development."
        );
        authorRepository.save(brianGoetz);

        Author timPeierls = new Author(
                3L,
                "Tim Peierls",
                "no explanation available"
        );
        authorRepository.save(timPeierls);

        Author joshuaBloch = new Author(
                4L,
                "Joshua Bloch",
                "Joshua J. Bloch is an American software engineer and a technology author, formerly employed at Sun Microsystems and Google. He led the design and implementation of numerous Java platform features, including the Java Collections Framework, the java.math package, and the assert mechanism."
        );
        authorRepository.save(joshuaBloch);

        Author josephBowbeer = new Author(
                5L,
                "Joseph Bowbeer",
                "no explanation available"
        );
        authorRepository.save(josephBowbeer);

        Author davidHolmes = new Author(
                6L,
                "David Holmes",
                "no explanation available"
        );
        authorRepository.save(davidHolmes);

        Author dougLea = new Author(
                7L,
                "Doug Lea",
                "Douglas S. Lea is a professor of computer science and current head of the computer science department at State University of New York at Oswego, where he specializes in concurrent programming and the design of concurrent data structures. "
        );
        authorRepository.save(dougLea);

        Author ericEvans = new Author(
                8L,
                "Eric Evans",
                "He is a famous author. His best known book is Domain Driven Design"
        );
        authorRepository.save(ericEvans);

        // books
        var authorsAlgo = new ArrayList<Author>();
        authorsAlgo.add(daniel);
        Book algo = new Book(
                1L,
                "Algorithmic Thinking",
                "A Problem-Based Introduction",
                "Algorithms",
                "1234567891",
                authorsAlgo,
                noStarch,
                "Algorithmic Thinking will teach you how to solve challenging programming problems and design your own algorithms. Daniel Zingaro, a master teacher, draws his examples from world-class programming competitions like USACO and IOI. You’ll learn how to classify problems, choose data structures, and identify appropriate algorithms. You’ll also learn how your choice of data structure, whether a hash table, heap, or tree, can affect runtime and speed up your algorithms; and how to adopt powerful strategies like recursion, dynamic programming, and binary search to solve challenging problems."
        );
        bookRepository.save(algo);

        var authorsConcurrency = new ArrayList<Author>();
        authorsConcurrency.add(brianGoetz);
        authorsConcurrency.add(timPeierls);
        authorsConcurrency.add(joshuaBloch);
        authorsConcurrency.add(josephBowbeer);
        authorsConcurrency.add(davidHolmes);
        authorsConcurrency.add(dougLea);

        Book javaConcurrency = new Book(
                2L,
                "Java Concurrency in Practice",
                "",
                "",
                "1234567892",
                authorsConcurrency,
                adWes,
                "Threads are a fundamental part of the Java platform. As multicore processors become the norm, using concurrency effectively becomes essential for building high-performance applications. Java SE 5 and 6 are a huge step forward for the development of concurrent applications, with improvements to the Java Virtual Machine to support high-performance, highly scalable concurrent classes and a rich set of new concurrency building blocks. In Java Concurrency in Practice, the creators of these new facilities explain not only how they work and how to use them, but also the motivation and design patterns behind them."
        );
        bookRepository.save(javaConcurrency);

        var authorsDDD = new ArrayList<Author>();
        authorsDDD.add(ericEvans);
        Book ddd = new Book(
                3L,
                "Domain-Driven Design",
                "Tackling Complexity in the Heart of Software",
                "",
                "1234567893",
                authorsDDD,
                adWes,
                "Domain-Driven Design fills that need. This is not a book about specific technologies. It offers readers a systematic approach to domain-driven design, presenting an extensive set of design best practices, experience-based techniques, and fundamental principles that facilitate the development of software projects facing complex domains. Intertwining design and development practice, this book incorporates numerous examples based on actual projects to illustrate the application of domain-driven design to real-world software development."
        );
        bookRepository.save(ddd);
    }
}
