package com.jkaninda.simpleapi.utils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
@AllArgsConstructor
@Component
@Slf4j
public class Util {

    public File readFIle(String filename, Path uploadPath) {
        Path file = uploadPath.resolve(filename);
        return new File(String.valueOf(file));
    }
}
