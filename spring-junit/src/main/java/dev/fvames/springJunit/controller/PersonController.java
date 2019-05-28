package dev.fvames.springJunit.controller;

import dev.fvames.springJunit.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 2019/5/28 16:42
 */
@RestController
public class PersonController {

    @Autowired
    private Person person;


    @GetMapping("/")
    public Person getPerson() {
        return person;
    }

}
