package dev.fvames.spring.cloud.feign.server.controller;

import dev.fvames.spring.cloud.feign.api.domain.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @version 2019/9/27 13:52
 */
@RestController
public class PersonController {

	private final Map<Long, Person> personsMap = new ConcurrentHashMap<>();

	@PostMapping(value = "/person/save")
	public boolean savePerson(@RequestBody Person person) {
		return personsMap.putIfAbsent(person.getId(), person) == null;
	}

	@GetMapping(value = "/person/find/all")
	public Collection<Person> findAll() {
		return personsMap.values();
	}

}
