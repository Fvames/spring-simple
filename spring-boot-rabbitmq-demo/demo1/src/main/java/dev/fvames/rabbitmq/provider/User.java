package dev.fvames.rabbitmq.provider;

import java.io.Serializable;

/**
 * TODO 类描述
 *
 * @version 2019/9/11 11:08
 */

public class User implements Serializable {

	private Long id;
	private String userName;
	private Integer age;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}
