package com.capgemini.starterkit.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.starterkit.datatype.BookType;
import com.capgemini.starterkit.entity.Book;
import com.capgemini.starterkit.mapper.BookMapper;
import com.capgemini.starterkit.mapper.common.CycleAvoidingMappingContext;
import com.capgemini.starterkit.repository.BookRepository;
import com.capgemini.starterkit.service.BookService;
import com.capgemini.starterkit.to.BookDto;

@Service
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private BookMapper bookMapper;

	@Override
	public Book findBookByIsbn(String isbn) {
		Book foundBook = bookRepository.findBookByIsbn(isbn);
		return foundBook;
	}

	@Override
	public void createBook(String isbn, String title) {
		Book b = new Book();
		b.setTitle(title);
		b.setIsbn(isbn);
		b.setType(BookType.ADVENTURE);
		bookRepository.saveBook(b);
	}

	@Override
	public void delete(BookDto bookDto) {
		Book book = bookMapper.bookDtoToBook(bookDto, CycleAvoidingMappingContext.create());
		bookRepository.deleteBook(book);

	}

	@Override
	public BookDto add(BookDto bookDto) {
		Book book = bookMapper.bookDtoToBook(bookDto, CycleAvoidingMappingContext.create());
		bookRepository.saveBook(book);
		return bookMapper.bookToBookDto(book, CycleAvoidingMappingContext.create());
	}

	@Override
	public BookDto update(BookDto bookDto) {
		Book book = bookMapper.bookDtoToBook(bookDto, CycleAvoidingMappingContext.create());
		bookRepository.updateBook(book);
		return bookMapper.bookToBookDto(book, CycleAvoidingMappingContext.create());
	}

}
