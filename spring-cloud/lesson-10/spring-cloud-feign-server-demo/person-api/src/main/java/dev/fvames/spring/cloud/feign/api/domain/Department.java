package dev.fvames.spring.cloud.feign.api.domain;

/**
 *
 * @version 2019/9/27 13:46
 */

public class Department {

	private Long id;
	private String deptName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Override
	public String toString() {
		return "Person{" +
				"id=" + id +
				", deptName='" + deptName + '\'' +
				'}';
	}
}
