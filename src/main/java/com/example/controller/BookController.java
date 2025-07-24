package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.BookDto;
import com.example.service.BookService;

@RestController
@RequestMapping("/book-store")
public class BookController {

		BookService bookService;
			
		public BookController(BookService bookService) {
			super();
			this.bookService = bookService;
		}
		
		@GetMapping("/welcome")
		public ResponseEntity<String> welcomeMessage() {
			return new ResponseEntity<>("Welcome to book store", HttpStatus.OK);
		}

		@GetMapping("/{bookId}")
		@PreAuthorize("hasRole('USER')")
		public ResponseEntity<BookDto> getBook(@PathVariable String bookId) {
			BookDto bookDto = bookService.getBook(bookId);
			return new ResponseEntity<>(bookDto,HttpStatus.OK);
		}
		
		@GetMapping("/")
		@PreAuthorize("hasRole('ADMIN')")
		public ResponseEntity<List<BookDto>> getAllBooks() {
			List<BookDto> booksList = bookService.getAllBooks();
			return new ResponseEntity<>(booksList,HttpStatus.OK);
		}
		
		@PostMapping("/")
		@PreAuthorize("hasRole('ADMIN')")
		public ResponseEntity<BookDto> createBooks(@RequestBody BookDto bookDto) {
			BookDto bookdto = bookService.createBook(bookDto);
			return new ResponseEntity<>(bookdto,HttpStatus.OK);
		}
		@PutMapping("/")
		@PreAuthorize("hasRole('ADMIN')")
		public ResponseEntity<BookDto> updateBook(BookDto bookDto) {
			BookDto bookDto1 = bookService.updateBookName(bookDto);
			return new ResponseEntity<>(bookDto1, HttpStatus.OK);
		}
		
		@DeleteMapping("/{bookId}")
		@PreAuthorize("hasRole('ADMIN')")
		public ResponseEntity<String> deleteBook(@PathVariable String bookId) {
			bookService.deleteBookByBookId(bookId);
			return new ResponseEntity<>("Book deleted successfully", HttpStatus.OK);
		}
}
