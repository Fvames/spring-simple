package dev.fvames.springbootvalidate.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValidCardNumberConstraintValidator.class)
public @interface ValidCardNumber {

	String message() default "{invalid.card.number.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
