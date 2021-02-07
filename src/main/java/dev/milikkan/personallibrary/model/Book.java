package dev.milikkan.personallibrary.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String subtitle;
    private String series;
    private String isbn;

    @ManyToMany
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "bookId"),
            inverseJoinColumns = @JoinColumn(name = "authorId"))
    private final List<Author> authors = new ArrayList<>();

    @ManyToOne
    private Publisher publisher;

    private String explanation;

    public Book(String title, String subtitle, String series, String isbn, Publisher publisher, String explanation) {
        this.title = title;
        this.subtitle = subtitle;
        this.series = series;
        this.isbn = isbn;
        this.publisher = publisher;
        this.explanation = explanation;
    }
}
