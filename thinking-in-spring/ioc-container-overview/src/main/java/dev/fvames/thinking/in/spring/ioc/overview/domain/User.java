package dev.fvames.thinking.in.spring.ioc.overview.domain;

/**
 * TODO 类描述
 *
 * @author
 * @version 2020/2/25 9:55
 */

public class User {
	private String id;
	private String name;

	public static User createUser() {
		User user = new User();
		user.setId("1");
		user.setName("static-user");
		return user;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				'}';
	}
}
