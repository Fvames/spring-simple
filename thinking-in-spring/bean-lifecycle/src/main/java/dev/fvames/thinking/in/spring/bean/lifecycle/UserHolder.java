package dev.fvames.thinking.in.spring.bean.lifecycle;

import dev.fvames.thinking.in.spring.ioc.overview.domain.User;

/**
 * UserHolder 类
 *
 * @author
 * @version 2020/4/2 15:45
 */

public class UserHolder {
	private final User user;

	private Integer number;

	private String description;

	public UserHolder(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "UserHolder{" +
				"user=" + user +
				", number=" + number +
				", description='" + description + '\'' +
				'}';
	}
}
