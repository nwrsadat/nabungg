package com.anwar.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ConfirmPasswordValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfirmPassword {
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
    public String message();
    public String password();
    public String passwordConfirmation();
}
