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
 * TODO 类描述
 *
 * @version 2019/9/18 14:40
 */
@RestController
public class PersonServerController {

	private final Map<Long, Person> personMap = new ConcurrentHashMap<>();

	@PostMapping("/person/save")
	public boolean save(@RequestBody Person person) {

		return personMap.putIfAbsent(person.getId(), person) == null;
	}

	@GetMapping("/person/find/all")
	public Collection<Person> findAll() {
		return personMap.values();
	}
}
