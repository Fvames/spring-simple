package dev.fvames.spring.cloud.feign.client.controller;

import dev.fvames.spring.cloud.feign.api.domain.Person;
import dev.fvames.spring.cloud.feign.api.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 *
 *
 * @version 2019/9/18 13:43
 */
@RestController
public class PersonController implements PersonService {

	private final PersonService personService;

	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@PostMapping("/person/save")
	@Override
	public boolean save(@RequestBody Person person) {
		return personService.save(person);
	}

	@GetMapping("/person/find/all")
	@Override
	public Collection<Person> findAll() {
		return personService.findAll();
	}
}
