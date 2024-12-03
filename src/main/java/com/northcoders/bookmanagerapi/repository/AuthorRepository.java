package com.northcoders.bookmanagerapi.repository;

import com.northcoders.bookmanagerapi.model.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthorRepository  extends CrudRepository<Author, Long> {

    Optional<Author> findByName(String name);

}
