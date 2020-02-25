package dev.fvames.thinking.in.spring.ioc.overview.domain;

import dev.fvames.thinking.in.spring.ioc.overview.annotation.Super;

/**
 * TODO 类描述
 *
 * @author
 * @version 2020/2/25 9:58
 */
@Super
public class SuperUser extends User{
	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "SuperUser{" +
				"address='" + address + '\'' +
				"} " + super.toString();
	}
}
