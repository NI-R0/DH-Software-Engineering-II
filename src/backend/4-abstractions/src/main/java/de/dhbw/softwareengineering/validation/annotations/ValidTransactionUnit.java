package de.dhbw.softwareengineering.validation.annotations;

import de.dhbw.softwareengineering.constants.Constants;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

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
@NotBlank(message = "'TransactionUnit' must not be blank.")
@NotEmpty(message = "'TransactionUnit' must not be empty.")
@NotNull(message = "'TransactionUnit' must not be null.")
@Size(min = Constants.UNIT_MIN_LENGTH, max = Constants.UNIT_MAX_LENGTH, message = "Length of 'TransactionUnit' must be between {min} and {max}.")
public @interface ValidTransactionUnit {
    String message() default "TRANSACTION_UNIT: String format invalid!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
