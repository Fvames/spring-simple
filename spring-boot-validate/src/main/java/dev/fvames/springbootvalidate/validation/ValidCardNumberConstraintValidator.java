package dev.fvames.springbootvalidate.validation;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * @version 2019/7/5 17:42
 */

public class ValidCardNumberConstraintValidator implements ConstraintValidator<ValidCardNumber, String> {


	/**
	 * 需求：通过员工卡号来校验，需要通过卡号的前缀和后缀来判断
	 *
	 * 前缀必须以 "LJ" 开头
	 *
	 * 后缀必须是数字
	 *
	 * @param value
	 * @param context
	 */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		String[] parts = StringUtils.split(value, "-");
		if (ArrayUtils.getLength(parts) != 2) {
			return false;
		}

		boolean isValidPrefix = Objects.equals("LJ", parts[0]);
		boolean isValidSubfix = StringUtils.isNumeric(parts[1]);

		return isValidPrefix & isValidPrefix;
	}
}
