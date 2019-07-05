package dev.fvames.springbootvalidate.model;

import dev.fvames.springbootvalidate.validation.ValidCardNumber;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 *
 * @version 2019/7/5 17:10
 */

public class User {

	@NotNull
	@Max(100)
	private Long id;
	private String userName;
	@ValidCardNumber(message = "卡号需要以 “LJ” 开头，以数字结尾。")
	private String cardNumber;

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

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
}
