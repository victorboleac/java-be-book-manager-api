package com.northcoders.bookmanagerapi.controller;

import com.northcoders.bookmanagerapi.model.Book;
import com.northcoders.bookmanagerapi.service.BookManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/book")
public class BookManagerController {

    @Autowired
    BookManagerService bookManagerService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookManagerService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book newBook = bookManagerService.insertBook(book);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("book", "/api/v1/book/" + newBook.getId().toString());
        return new ResponseEntity<>(newBook, httpHeaders, HttpStatus.CREATED);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> findBookById(@PathVariable Long bookId)
    {
        // bookManagerService
        Optional<Book> bookById = bookManagerService.getBookById(bookId);
        return new ResponseEntity<>(bookById.orElse(null),HttpStatus.OK);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<Book> updateBookById(@RequestBody Book book, @PathVariable Long bookId)
    {
        if (bookManagerService.getBookById(bookId).isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (Objects.equals(book.getId(), bookId))  return new ResponseEntity<>(bookManagerService.updateBook(book),HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @DeleteMapping("/{bookId}")
    public ResponseEntity<?> deleteBookById(@PathVariable Long bookId)
    {
        if (bookManagerService.deleteBookById(bookId)) return new ResponseEntity<>(bookId+" was deleted.",HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);


    }
}
