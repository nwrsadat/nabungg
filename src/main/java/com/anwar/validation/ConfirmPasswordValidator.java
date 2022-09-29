package com.anwar.validation;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConfirmPasswordValidator implements ConstraintValidator<ConfirmPassword, Object> {

    private String password;
    private String passwordConfirmation;

    @Override
    public void initialize(ConfirmPassword constraintAnnotation) {
        this.password = constraintAnnotation.password();
        this.passwordConfirmation = constraintAnnotation.passwordConfirmation();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        String inputPassword = new BeanWrapperImpl(value).getPropertyValue(password).toString();
        String inputPasswordConfirmation = new BeanWrapperImpl(value).getPropertyValue(passwordConfirmation).toString();

        return inputPassword.equals(inputPasswordConfirmation);
    }
}
