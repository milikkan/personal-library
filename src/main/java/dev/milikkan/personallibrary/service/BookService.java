package dev.milikkan.personallibrary.service;

import dev.milikkan.personallibrary.entity.Author;
import dev.milikkan.personallibrary.entity.Book;
import dev.milikkan.personallibrary.entity.BookSearch;
import dev.milikkan.personallibrary.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        String bookName = bookSearch.getBookName().strip();
        String bookSeries = bookSearch.getBookSeries().strip();
        String bookIsbn = bookSearch.getIsbn().strip();
        String bookAuthorName = bookSearch.getAuthorName().strip();

        if (bookName.isBlank() && bookSeries.isBlank() && bookIsbn.isBlank() && bookAuthorName.isBlank()) {
            return List.of();
        }

        return bookRepository
                .findDistinctByTitleContainingAndIsbnContainingAndSeriesContainingAndAuthorsFullNameContaining(
                        bookName,
                        bookIsbn,
                        bookSeries,
                        bookAuthorName
                );
    }

    // check if there is any author without name
    public boolean checkAuthorWithoutName(List<Author> authors) {
        return authors.stream()
                .anyMatch(author -> author.getFullName().isBlank());
    }

}
