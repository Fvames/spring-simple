package dev.fvames.springbootjdbc.controller;

import dev.fvames.springbootjdbc.domain.Blog;
import dev.fvames.springbootjdbc.repository.BlogRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 2019/6/11 17:57
 */
@RestController
public class BlogController {

    private final BlogRepository blogRepository;

    public BlogController(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @PostMapping(value = "/web/mvc/blog/save", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Boolean save(@RequestBody Blog blog) {
        System.out.println(">>>> blog save");

        return blogRepository.transactionalSave(blog);
    }
}
