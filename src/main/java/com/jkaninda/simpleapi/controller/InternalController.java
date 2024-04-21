package com.jkaninda.simpleapi.controller;

import com.jkaninda.simpleapi.entity.Fake;
import com.jkaninda.simpleapi.repository.BookRepository;
import com.jkaninda.simpleapi.repository.FakeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("internal")
@Slf4j
public class InternalController {
    private final FakeRepository fakeRepository;
    @GetMapping("health/ready")
    ResponseEntity<String> ready(){
        //Test Database connection
        Fake fake = new Fake("1","Simple API Health Check");
        fakeRepository.save(fake);
        log.info("Health:: Application is ready to start receiving requests");
        return ResponseEntity.ok("Ready");
    }
    @GetMapping("health/live")
    ResponseEntity<String> live(){
        //Test Database connection
        Fake fake = new Fake("1","Simple API Health Check");
        fakeRepository.save(fake);
        //
        long count=fakeRepository.count();
        log.info("Health::  Application Health check, count: {}", count);
        log.info("Health::  Application is running and everything is OK");
        return ResponseEntity.ok("Running...");
    }



}
