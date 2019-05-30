package dev.fvames.messageconverteronspringmvc.controller;

import dev.fvames.messageconverteronspringmvc.domain.Person;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 必须指明 @RequestBody
 * @version 2019/5/29 16:06
 */
@RestController
public class PersonController {

    @GetMapping("/person")
    public Person getPerson() {

        return new Person(1L, "艾迪1");
    }

    @PostMapping(value = "/person/properties-to-json",
            consumes = "application/properties+person",// 对应浏览器 Content-type
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE // 对应浏览器自描述信息 Accept
    )
    public Person propertiesToJson(@RequestBody Person person) {
        return person;
    }

    @PostMapping("/perosn/json-to-properties")
    public Person jsonToProperties(@RequestBody Person person) {
        return person;
    }

}
