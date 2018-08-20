package com.capgemini.starterkit.service;

import com.capgemini.starterkit.entity.Book;
import com.capgemini.starterkit.to.BookDto;

public interface BookService {
	
	public Book findBookByIsbn(String isbn);
	
	public void createBook(String isbn, String title);

	public void delete(BookDto bookDto);

	BookDto add(BookDto bookDto);

	BookDto update(BookDto bookDto);
}
