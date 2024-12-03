package com.northcoders.bookmanagerapi.service;

import com.northcoders.bookmanagerapi.model.Author;

import java.util.Optional;

public interface AuthorService {
    Optional<Author> findAuthorByName(String name);
    Author createAuthor(Author author);
}
