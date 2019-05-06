package dev.fvames.springbootmvc.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestControllerAdvicer {

    @ExceptionHandler(NullPointerException.class)
    public Object handlerNPE(Throwable throwable) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", throwable.getMessage());
        return map;
    }

}
