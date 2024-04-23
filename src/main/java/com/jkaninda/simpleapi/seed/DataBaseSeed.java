package com.jkaninda.simpleapi.seed;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jkaninda.simpleapi.entity.Book;
import com.jkaninda.simpleapi.repository.BookRepository;
import com.jkaninda.simpleapi.utils.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class DataBaseSeed {
    private final BookRepository bookRepository;
    ObjectMapper objectMapper = new ObjectMapper();
    private final Util util;

    List<Book> books = new ArrayList<>();

    @Async
    public void run() {
        //Run Data base seed
        if (ObjectUtils.isEmpty(bookRepository.findAll())) {
            File file = util.readFIle ("books.json", Path.of("data"));
            log.info("Seed:: Inserting Books...");
            try {
                books = objectMapper.readValue(file, new TypeReference<>() {
                });
                bookRepository.saveAll(books);
                log.info("Seed:: Books Inserted");
                log.info("Seed:: Database seed successfully completed");
            } catch (IOException e) {
                log.error("Seed:: Error reading file", e);
            }


        }
    }



}
