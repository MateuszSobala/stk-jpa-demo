package com.capgemini.starterkit.to;

import java.util.ArrayList;
import java.util.List;

public class AuthorDto extends AbstractDto {

    private List<BookDto> book;

    private String firstName;

    private String lastName;

    public List<BookDto> getBook() {
        if (book == null) {
            book = new ArrayList<>();
        }
        return book;
    }

    public void setBook(List<BookDto> book) {
        this.book = book;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
