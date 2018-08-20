package com.capgemini.starterkit.to;

import java.util.List;

public class LibraryDto extends AbstractDto {

    private String name;

    private List<BookDto> books;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BookDto> getBooks() {
        return books;
    }

    public void setBooks(List<BookDto> books) {
        this.books = books;
    }
}
