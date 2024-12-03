package com.northcoders.bookmanagerapi.service;

import com.northcoders.bookmanagerapi.model.Author;
import com.northcoders.bookmanagerapi.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public Optional<Author> findAuthorByName(String name){
        return authorRepository.findByName(name);
    }

    @Override
    public Author createAuthor(Author author){
        return authorRepository.save(author);
    }
}
