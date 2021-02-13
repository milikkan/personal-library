package dev.milikkan.personallibrary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Kitap bulunamadı")
public class BookNotFoundException extends RuntimeException {
    private final Long id;

    public BookNotFoundException(Long id) {
        super("Kitap bulunamadı. Kitap #" + id);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
