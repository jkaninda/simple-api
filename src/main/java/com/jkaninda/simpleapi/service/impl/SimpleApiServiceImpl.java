package com.jkaninda.simpleapi.service.impl;

import com.jkaninda.simpleapi.dto.ResponseDto;
import com.jkaninda.simpleapi.dto.ResponseError;
import com.jkaninda.simpleapi.entity.Book;
import com.jkaninda.simpleapi.exceptions.NotFoundException;
import com.jkaninda.simpleapi.repository.BookRepository;
import com.jkaninda.simpleapi.service.SimpleApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SimpleApiServiceImpl implements SimpleApiService {
    private final BookRepository bookRepository;

    @Value("${api.version}")
    private String apiVersion;

    @Override
    public ResponseDto<String> home() {
        log.info("API Path: / || Home page ");
        return ResponseDto.<String>builder()
                .success(true)
                .code(200)
                .message("Hello from Simple API, everything is ok")
                .data(null)
                .build();
    }

    @Override
    public ResponseDto<List<Book>> books() {
        log.info("API Path: /books || Get all books ");
        return ResponseDto.<List<Book>>builder()
                .success(true)
                .code(200)
                .message("OK")
                .data((List<Book>) bookRepository.findAll())
                .build();
    }

    @Override
    public ResponseDto<List<Book>> findBooksByAuth(String author) {
        return ResponseDto.<List<Book>>builder()
                .success(true)
                .code(200)
                .message("OK")
                .data(bookRepository.findByAuthor(author))
                .build();
    }

    @Override
    public ResponseDto<Book> addBook(Book book) {
        log.info("API Path: /books || Create Book ");
        //Set Date
        book.setCreatedAt(new Date());
        book.setUpdatedAt(new Date());
        return ResponseDto.<Book>builder()
                .success(true)
                .code(201)
                .message("Created")
                .data(bookRepository.save(book))
                .build();
    }

    @Override
    public ResponseDto<Book> updateBook(UUID id, Book book) {
        Optional<Book> optionalBook=bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            book.setId(id);
            book.setCreatedAt(optionalBook.get().getCreatedAt());
            book.setUpdatedAt(new Date());
            log.info("API Path: /books/{id} || Boot has been updated");
            return ResponseDto.<Book>builder()
                    .success(true)
                    .code(200)
                    .message("Book has been updated")
                    .data(bookRepository.save(book))
                    .build();
        }
        log.info("API Path: /books/{id} || Boot not found with ID: {} , can't update", id);
        throw new  NotFoundException("Book not found");
    }

    @Override
    public ResponseDto<Book> getBookById(UUID id) {
        log.info("API Path: /books/{id} || Find book by Id");
        Optional<Book> book=bookRepository.findById(id);
        if (book.isPresent()) {
            return ResponseDto.<Book>builder()
                    .success(true)
                    .code(200)
                    .message("OK")
                    .data(book.get())
                    .build();
        }
        log.info("API Path: /books/{id} || Boot not found with ID: {}", id);
        throw new  NotFoundException("Book not found");
    }

    @Override
    public ResponseDto<String> deleteBook(UUID id) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new NotFoundException("Book not found"));
        bookRepository.delete(book);
        log.info("API Path: /books/{id} || Delete book with ID: {} ", id);
        return ResponseDto.<String>builder()
                .success(true)
                .code(200)
                .message("Book with id: " + id + " deleted")
                .data(null)
                .build();
    }

    @Override
    public ResponseDto<String> deleteAllBooks() {
        List<Book> books= (List<Book>) bookRepository.findAll();
        log.info("API Path: /deleteAll || Deleting all books");
        if(books.isEmpty()) {
            log.error("API Path: /deleteAll || There are no books in the database ");
            return ResponseDto.<String>builder()
                    .success(false)
                    .code(200)
                    .message("There are no books in the database")
                    .error(new ResponseError("ERROR_NO_BOOKS","There are no books in the database"))
                    .data(null)
                    .build();

        }
        bookRepository.deleteAll(books);
        log.info("API Path: /deleteAll || All data have been deleted ");
        return ResponseDto.<String>builder()
                .success(true)
                .code(200)
                .message("All books have been deleted, restart the application and if Database seed is enabled, the predefined data will be restored\n")
                .data(null)
                .build();

    }

    @Override
    public String version() {
        log.info("API Path: /version || Get API Book ");
        return "Version: " + apiVersion;
    }
}
