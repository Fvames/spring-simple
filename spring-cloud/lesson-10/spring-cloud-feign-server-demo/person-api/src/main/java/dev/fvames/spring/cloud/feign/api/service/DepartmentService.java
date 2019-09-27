package dev.fvames.spring.cloud.feign.api.service;

import dev.fvames.spring.cloud.feign.api.domain.Department;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;

/**
 *
 * @version 2019/9/27 13:47
 */
@FeignClient(value = "person-server")
public interface DepartmentService {

	@PostMapping(value = "/dept/save")
	boolean save(@RequestBody Department dept);

	@GetMapping(value = "/dept/find/all")
	Collection<Department> findAll();
}
