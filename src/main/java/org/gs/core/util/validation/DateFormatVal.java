package com.dimata.service.general.harisma.core.util.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateFormatValidation.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateFormatVal {
    String message() default "Wrong Date format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
