package com.jkaninda.simpleapi.controller;

import com.jkaninda.simpleapi.dto.ResponseDto;
import com.jkaninda.simpleapi.entity.Book;
import com.jkaninda.simpleapi.service.SimpleApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class SimpleApiController {
    private final SimpleApiService simpleApiService;

    @GetMapping("/")
    ResponseEntity<ResponseDto<String>> home(){
        return ResponseEntity.ok(simpleApiService.home());
    }

    //Books
    @GetMapping("books")
    ResponseEntity<ResponseDto<List<Book>>> getBooks(){
        return ResponseEntity.ok(simpleApiService.books());
    }
    @GetMapping("books/{id}")
    ResponseEntity<ResponseDto<Book>> getBookById(@PathVariable("id") UUID id){

        return ResponseEntity.ok(simpleApiService.getBookById(id));
    }
    @DeleteMapping("books/{id}")
    ResponseEntity<ResponseDto<String>> deleteBook(@PathVariable("id") UUID id){

        return ResponseEntity.ok(simpleApiService.deleteBook(id));
    }
    @PostMapping("books")
    ResponseEntity<ResponseDto<Book>> addBook(@RequestBody Book book){
        return ResponseEntity.status(HttpStatus.CREATED).body(simpleApiService.addBook(book));
    }

    @PutMapping("books/{id}")
    ResponseEntity<ResponseDto<Book>> updateBook(@PathVariable("id") UUID id,@RequestBody Book book){
        return ResponseEntity.ok(simpleApiService.updateBook(id,book));
    }
    //Clean up
    @DeleteMapping("deleteAll")
    ResponseEntity<ResponseDto<String>> deleteAll(){
        return ResponseEntity.ok(simpleApiService.deleteAllBooks());
    }
    // API Version
    @GetMapping("version")
    ResponseEntity<String> version(){
        return ResponseEntity.ok(simpleApiService.version());
    }
}
