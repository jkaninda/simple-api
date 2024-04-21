package com.jkaninda.simpleapi.repository;

import com.jkaninda.simpleapi.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends CrudRepository<Book, UUID> {

    List<Book> findByAuthor(String author);
    List<Book> findByTitleIgnoreCaseContaining(String title);
}
