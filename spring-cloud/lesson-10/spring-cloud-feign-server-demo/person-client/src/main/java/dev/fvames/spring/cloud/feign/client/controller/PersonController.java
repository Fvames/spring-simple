package dev.fvames.spring.cloud.feign.client.controller;

import dev.fvames.spring.cloud.feign.api.domain.Person;
import dev.fvames.spring.cloud.feign.api.service.PersonService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @version 2019/9/27 14:15
 */
@RestController
public class PersonController implements PersonService {

	private final PersonService personService;

	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@Override
	public boolean save(@RequestBody Person person) {
		return personService.save(person);
	}

	@Override
	public Collection<Person> findAll() {
		return personService.findAll();
	}
}
