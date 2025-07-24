package com.example.service;

import java.util.List;

import com.example.dto.BookDto;

public interface BookService {
	
	public BookDto getBook(String bookId);
	
	public List<BookDto> getAllBooks();
	
	public BookDto createBook(BookDto bookDto);
	
	public BookDto updateBookName(BookDto bookDto);
	
	public void deleteBookByBookId(String bookId);
}
