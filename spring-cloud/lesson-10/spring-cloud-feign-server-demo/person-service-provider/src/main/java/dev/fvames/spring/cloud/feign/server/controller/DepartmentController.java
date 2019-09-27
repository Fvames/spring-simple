package dev.fvames.spring.cloud.feign.server.controller;

import dev.fvames.spring.cloud.feign.api.domain.Department;
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
public class DepartmentController {

	private final Map<Long, Department> deptsMap = new ConcurrentHashMap<>();

	@PostMapping(value = "/dept/save")
	public boolean saveDepartment(@RequestBody Department dept) {
		return deptsMap.putIfAbsent(dept.getId(), dept) == null;
	}

	@GetMapping(value = "/dept/find/all")
	public Collection<Department> findAll() {
		return deptsMap.values();
	}

}
