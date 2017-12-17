package com.ifood.backend.advancedtest.api.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class InRangeValidator implements ConstraintValidator<InRange, Float> {

    private Float min;
    private Float max;

    @Override
    public void initialize(InRange constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Float value, ConstraintValidatorContext context) {
        return value == null || (value >= min && value <= max);
    }
}
