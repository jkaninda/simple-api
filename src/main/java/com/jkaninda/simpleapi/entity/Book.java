package com.jkaninda.simpleapi.entity;

import lombok.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@RedisHash(value = "books")
public class Book implements Serializable {
    @Id
    @Indexed
    private UUID id;
    private String title;
    private Integer year;
    private String author;
    private String country;
    private String imageLink;
    private String language;
    private String link;
    private Integer pages;
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;
}

