package dev.milikkan.personallibrary.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, message = "Kitap adı en az 1 karakter içermelidir")
    private String title;

    private String subtitle;
    private String series;

    @NotNull
    @Size(min = 10, message = "ISBN en az 10 en karakter içermelidir")
    @Size(max = 13, message = "ISBN en fazla 13 karakter içermelidir")
    private String isbn;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "bookId"),
            inverseJoinColumns = @JoinColumn(name = "authorId"))
    private List<Author> authors = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Publisher publisher;

    @Column(length = 4096)
    private String explanation;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", series='" + series + '\'' +
                ", isbn='" + isbn + '\'' +
                ", explanation='" + explanation + '\'' +
                '}';
    }
}
