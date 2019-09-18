package dev.fvames.spring.cloud.feign.api.hystric;

import dev.fvames.spring.cloud.feign.api.domain.Person;
import dev.fvames.spring.cloud.feign.api.service.PersonService;

import java.util.Collection;
import java.util.Collections;

/**
 * TODO 类描述
 *
 * @version 2019/9/18 16:47
 */

public class PersonServiceFallback implements PersonService {
	@Override
	public boolean save(Person person) {
		return false;
	}

	@Override
	public Collection<Person> findAll() {
		return Collections.emptyList();
	}
}
