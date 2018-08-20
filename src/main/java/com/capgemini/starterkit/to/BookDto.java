package com.capgemini.starterkit.to;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.starterkit.datatype.BookType;

public class BookDto extends AbstractDto {

    private String isbn;

    private String title;

    private BookType type;

    private int year;

    private boolean active;

    private List<AuthorDto> author;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BookType getType() {
        return type;
    }

    public void setType(BookType type) {
        this.type = type;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<AuthorDto> getAuthor() {
        if (author == null) {
            author = new ArrayList<>();
        }
        return author;
    }

    public void setAuthor(List<AuthorDto> author) {
        this.author = author;
    }
}
