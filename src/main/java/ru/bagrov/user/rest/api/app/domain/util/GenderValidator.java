package ru.bagrov.user.rest.api.app.domain.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class GenderValidator implements ConstraintValidator<GenderValue, String> {

    private String[] allowedValues;

    @Override
    public void initialize(GenderValue constraintAnnotation) {
        this.allowedValues = constraintAnnotation.allowedValues();
    }
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && Arrays.stream(allowedValues).anyMatch(val -> val.equals(value));
    }
}
