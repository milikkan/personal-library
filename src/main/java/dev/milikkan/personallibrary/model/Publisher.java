package dev.milikkan.personallibrary.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "publisher")
    private final List<Book> books = new ArrayList<>();

    private String explanation;

    public Publisher(String name, String explanation) {
        this.name = name;
        this.explanation = explanation;
    }
}
