package dev.fvames.messageconverteronspringmvc.controller;

import dev.fvames.messageconverteronspringmvc.domain.Person;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 2019/5/29 16:06
 */
@RestController
public class PersonController {

    @GetMapping("/person")
    public Person getPerson() {

        return new Person(1L, "艾迪1");
    }

    @PostMapping(value = "/person/properties-to-json",
            consumes = "application/properties+person",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Person propertiesToJson(Person person) {
        return person;
    }

    @PostMapping("/perosn/json-to-properties")
    public Person jsonToProperties(Person person) {
        return person;
    }

}
