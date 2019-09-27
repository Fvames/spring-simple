package dev.fvames.spring.cloud.feign.client.controller;

import dev.fvames.spring.cloud.feign.api.domain.Department;
import dev.fvames.spring.cloud.feign.api.service.DepartmentService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class DepartmentController implements DepartmentService {

	private final DepartmentService deptService;

	public DepartmentController(DepartmentService deptService) {
		this.deptService = deptService;
	}

	@Override
	public boolean save(@RequestBody Department department) {
		return deptService.save(department);
	}

	@Override
	public Collection<Department> findAll() {
		return deptService.findAll();
	}
}
