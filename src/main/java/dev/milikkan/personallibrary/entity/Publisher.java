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
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, message = "Yayınevi adı en az 1 karakter içermelidir")
    private String name;

    @OneToMany(mappedBy = "publisher")
    private final List<Book> books = new ArrayList<>();

    @Column(length = 2048)
    private String explanation;
}
