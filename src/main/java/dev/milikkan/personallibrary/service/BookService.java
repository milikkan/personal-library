package dev.milikkan.personallibrary.service;

import dev.milikkan.personallibrary.entity.Book;
import dev.milikkan.personallibrary.entity.BookSearch;
import dev.milikkan.personallibrary.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public void delete(Book book) {
        bookRepository.delete(book);
    }

    public List<Book> search(BookSearch bookSearch) {
        String bookName = bookSearch.getBookName();
        if (bookName == null) return new ArrayList<>();
        return this.findAll().stream()
                .filter(book -> book.getTitle().equals(bookName))
                .collect(Collectors.toList());
    }
}
