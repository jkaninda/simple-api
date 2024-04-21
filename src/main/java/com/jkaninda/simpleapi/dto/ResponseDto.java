package com.jkaninda.simpleapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto<T> {
    private Boolean success;
    private int code;
    private String message;
    private ResponseError error;
    private T data;
}
