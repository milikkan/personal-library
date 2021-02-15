package dev.milikkan.personallibrary.entity;

import lombok.Data;

@Data
public class BookSearch {
    private String bookName;
    private String bookSeries;
    private String authorName;
    private String isbn;
}
