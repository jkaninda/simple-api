package com.jkaninda.simpleapi.service;

import com.jkaninda.simpleapi.dto.ResponseDto;
import com.jkaninda.simpleapi.entity.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SimpleApiService {
    ResponseDto<String> home();
    ResponseDto<List<Book>> books();
    ResponseDto<List<Book>> findBooksByAuth(String author);
    ResponseDto<Book> addBook(Book book);
    ResponseDto<Book> updateBook(UUID id, Book book);
    ResponseDto<Book> getBookById(UUID id);
    ResponseDto<String> deleteBook(UUID id);
    ResponseDto<String> deleteAllBooks();
    String version();

}
