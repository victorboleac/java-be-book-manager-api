package com.northcoders.bookmanagerapi.service;

import com.northcoders.bookmanagerapi.model.Author;
import com.northcoders.bookmanagerapi.model.Book;
import com.northcoders.bookmanagerapi.repository.AuthorRepository;
import com.northcoders.bookmanagerapi.repository.BookManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookManagerServiceImpl implements BookManagerService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookManagerRepository bookManagerRepository;



    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        bookManagerRepository.findAll().forEach(books::add);
        return books;
    }

    @Override
    public Book insertBook(Book book) {
        Author author = book.getAuthor();
        Optional<Author> existingAuthor = authorRepository.findByName(author.getName());
        if (existingAuthor.isPresent()) book.setAuthor(existingAuthor.get());
        else {
            Author newAuthor = Author.builder()
                    .name(author.getName())
                    .funFact("")
                    .build();
            book.setAuthor(authorRepository.save(newAuthor));
        }

        return bookManagerRepository.save(book);
    }

    @Override
    public Optional<Book> getBookById(Long bookId) {
        //test null bookId behaviour
        return bookManagerRepository.findById(bookId);
    }

    @Override
    public Book updateBook(Book book) {
        return bookManagerRepository.save(book);
    }
    @Override
    public boolean deleteBookById(Long bookId) {
        if (bookManagerRepository.existsById(bookId)) {
            bookManagerRepository.deleteById(bookId);
            return true;
        }
        return false;

    }


}
