package de.dhbw.softwareengineering.validation.annotations;

import de.dhbw.softwareengineering.constants.Constants;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = {}
)
@Valid
@PastOrPresent(message = "'Timestamp' must not be in the future.")
@NotNull
public @interface ValidTransactionTimestamp {
    String message() default "TRANSACTION_DESCRIPTION: String format invalid!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
