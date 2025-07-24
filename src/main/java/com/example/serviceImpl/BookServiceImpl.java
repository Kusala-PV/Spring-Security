package com.example.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dto.BookDto;
import com.example.entity.Book;
import com.example.mapper.BookMapper;
import com.example.repository.BookRepository;
import com.example.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	
	BookRepository bookRepository;
	
	public BookServiceImpl(BookRepository bookRepository) {
//		super();
		this.bookRepository = bookRepository;
	}

	@Override
	public BookDto getBook(String bookId) {
		Book book = bookRepository.findBookByBookId(bookId);
		BookDto bookDto = BookMapper.toDto(book);
		return bookDto;
	}

	@Override
	public List<BookDto> getAllBooks() {
		List<Book> books = bookRepository.findAll();
		List<BookDto> bookDto = new ArrayList<>();
		for(Book book: books) {
			bookDto.add(BookMapper.toDto(book));
		}
		return bookDto;
	}

	@Override
	public BookDto createBook(BookDto bookDto) {
		Book book = bookRepository.insert(BookMapper.toEntity(bookDto));
		return BookMapper.toDto(book);
	}

	@Override
	public BookDto updateBookName(BookDto bookDto) {
		bookRepository.updateBookNameByBookId(bookDto.bookId(), bookDto.name());
		Book book = bookRepository.findBookByBookId(bookDto.bookId());
		return BookMapper.toDto(book);
	}

	@Override
	public void deleteBookByBookId(String bookId) {
		bookRepository.deleteBookByBookId(bookId);
	}

}
