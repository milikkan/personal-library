package dev.milikkan.personallibrary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleException() {
        return "error/error";
    }

    @ExceptionHandler(BookNotFoundException.class)
    public String handleBookNotFoundException() {
        return "error/book-not-found-error";
    }

    @ExceptionHandler(PublisherNotFoundException.class)
    public String handlePublisherNotFoundException() {
        return "error/publisher-not-found-error";
    }

    @ExceptionHandler(AuthorNotFoundException.class)
    public String handleAuthorNotFoundException() {
        return "error/author-not-found-error";
    }
}
