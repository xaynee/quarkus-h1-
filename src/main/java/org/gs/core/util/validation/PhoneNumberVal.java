package com.dimata.service.general.harisma.core.util.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Validasi no telp. Null di anggap true.
 */
@Documented
@Constraint(validatedBy = PhoneNumberValidation.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumberVal {
    String message() default "Wrong Phone number format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
