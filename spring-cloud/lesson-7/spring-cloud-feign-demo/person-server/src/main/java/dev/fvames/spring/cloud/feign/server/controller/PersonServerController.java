package dev.fvames.spring.cloud.feign.server.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import dev.fvames.spring.cloud.feign.api.domain.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * TODO 类描述
 *
 * @version 2019/9/18 14:40
 */
@RestController
public class PersonServerController {

	Random random = new Random();
	private final Map<Long, Person> personMap = new ConcurrentHashMap<>();

	@PostMapping("/person/save")
	public boolean save(@RequestBody Person person) {

		return personMap.putIfAbsent(person.getId(), person) == null;
	}

	@GetMapping("/person/find/all")
	@HystrixCommand(fallbackMethod = "fallbackForFindAllPersons",
	commandProperties = {
		@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100")
	})
	public Collection<Person> findAll() throws Exception{
		int value = random.nextInt(200);
		System.err.printf("person find all method sleep %d ms. \n", value);

		TimeUnit.MILLISECONDS.sleep(value);

		return personMap.values();
	}

	public Collection<Person> fallbackForFindAllPersons() {
		System.err.println("fallbackForFindAllPersons() is invoked!");
		return Collections.emptyList();
	}
}
