package dev.milikkan.personallibrary.repository;

import dev.milikkan.personallibrary.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findDistinctByTitleContainingAndIsbnContainingAndSeriesContainingAndAuthorsFullNameContaining(
            String title,
            String isbn,
            String series,
            String authorsFullName
    );

}
