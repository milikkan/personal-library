package dev.milikkan.personallibrary.exception;

import dev.milikkan.personallibrary.entity.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Yayınevi bulunamadı")
public class PublisherNotFoundException extends RuntimeException {
    private final Long id;

    public PublisherNotFoundException(Long id) {
        super("Yayınevi bulunamadı. Yayınevi #" + id);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
