package dev.fvames.springbootmvc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RestDemoController {

    @GetMapping("/404.html")
    public Map<String, Object> handlerPageNotFound(HttpStatus httpStatus, HttpServletRequest request, Throwable throwable) {

        Map<String, Object> map = new HashMap<>();
        map.put("statusCode", request.getAttribute("javax.servlet.error.status_code"));
        map.put("exceptionType", request.getAttribute("javax.servlet.error.exception_type"));
        map.put("message", request.getAttribute("javax.servlet.error.message"));
        map.put("throwableException", request.getAttribute("javax.servlet.error.exception"));
        map.put("throwable", throwable);
        map.put("requestUri", request.getAttribute("javax.servlet.error.request_uri"));
        map.put("servletName", request.getAttribute("javax.servlet.error.servlet_name"));
        map.put("httpStatus", httpStatus);

        return map;
    }
}
