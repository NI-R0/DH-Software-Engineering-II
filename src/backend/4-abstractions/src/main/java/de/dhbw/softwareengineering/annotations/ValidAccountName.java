package de.dhbw.softwareengineering.annotations;

import de.dhbw.softwareengineering.constants.Constants;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
@NotNull
@NotBlank
@NotEmpty
@Size(max = Constants.ACCOUNT_NAME_MAX_LENGTH)
@Size(min = Constants.ACCOUNT_NAME_MIN_LENGTH)
public @interface ValidAccountName {
    String message() default "ACCOUNT_NAME: String format invalid!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
