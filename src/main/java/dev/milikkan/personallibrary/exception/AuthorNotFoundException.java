package dev.milikkan.personallibrary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Yazar bulunamadı")
public class AuthorNotFoundException extends RuntimeException {
    private final Long id;

    public AuthorNotFoundException(Long id) {
        super("Yazar bulunamadı. Yazar #" + id);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
