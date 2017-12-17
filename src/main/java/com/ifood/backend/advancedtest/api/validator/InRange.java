package com.ifood.backend.advancedtest.api.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { InRangeValidator.class })
public @interface InRange {
    String message() default "Value is out of range";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    float min() default Float.MIN_VALUE;

    float max() default Float.MAX_VALUE;
}
