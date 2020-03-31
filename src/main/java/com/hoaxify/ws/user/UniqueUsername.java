package com.hoaxify.ws.user;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created By Yasin Memic on Mar, 2020
 */
@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = { UniqueUsernameValidator.class })
public @interface UniqueUsername {

    String message() default "{hoaxify.constraint.username.UniqueUsername.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
