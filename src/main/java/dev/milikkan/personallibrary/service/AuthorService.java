package dev.milikkan.personallibrary.service;

import dev.milikkan.personallibrary.entity.Author;
import dev.milikkan.personallibrary.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    public Author save(Author author) {
        return authorRepository.save(author);
    }

    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }

    public List<Author> sanitizeAuthorList(List<Author> incomingList, boolean willSaveNewAuthor) {
        var sanitizedList = new ArrayList<Author>();

        for (Author author : incomingList) {
            var found = this.findAll().stream()
                    .filter(a -> a.getFullName().equals(author.getFullName()))
                    .filter(a -> a.getExplanation().equals(author.getExplanation()))
                    .findAny();
            if (found.isPresent()) {
                sanitizedList.add(found.get());
            } else {
                if (willSaveNewAuthor) {
                    this.save(author);
                }
                sanitizedList.add(author);
            }
        }
        return sanitizedList;
    }
}
