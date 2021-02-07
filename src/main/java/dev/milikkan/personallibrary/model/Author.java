package dev.milikkan.personallibrary.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String explanation;

    @ManyToMany(mappedBy = "authors")
    private final List<Book> books = new ArrayList<>();

    public Author(String fullName, String explanation) {
        this.fullName = fullName;
        this.explanation = explanation;
    }
}
