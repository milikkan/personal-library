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
        return "error";
    }

    @ExceptionHandler(BookNotFoundException.class)
    public String handleBookNotFoundException() {
        return "book-not-found-error";
    }


}
