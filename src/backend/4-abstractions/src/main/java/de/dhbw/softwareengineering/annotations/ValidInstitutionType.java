package de.dhbw.softwareengineering.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

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
@NotNull(message = "'InstitutionType' must not be null.")
@NotBlank(message = "'InstitutionType' must not be blank.")
@NotEmpty(message = "'InstitutionType' must not be empty.")
public @interface ValidInstitutionType {
    String message() default "INSTITUTION_TYPE: Format invalid!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
