package com.jkaninda.simpleapi.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@RedisHash(value = "fake", timeToLive = 120)
public class Fake implements Serializable {
    //Fake entity for Kubernetes Database heath check
    @Id
    @Indexed
    private String id;
    private String name;
}

