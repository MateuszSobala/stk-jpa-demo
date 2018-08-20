package com.capgemini.starterkit.repository;

import java.util.List;

import com.capgemini.starterkit.entity.Book;

public interface BookRepository {
	
	public Book findBookByIsbn(String isbn);
	
	public void saveBook(Book book);

	void updateBook(Book book);

	public void deleteBook(Book book);

}
