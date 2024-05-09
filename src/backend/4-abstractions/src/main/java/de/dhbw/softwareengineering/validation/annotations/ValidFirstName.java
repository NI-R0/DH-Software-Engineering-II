package de.dhbw.softwareengineering.validation.annotations;


import de.dhbw.softwareengineering.constants.Constants;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = {}
)
@Valid
@NotNull(message = "'FirstName' must not be null.")
@NotEmpty(message = "'FirstName' must not be empty.")
@NotBlank(message = "'FirstName' must not be blank.")
@Size(min = Constants.OWNER_FIRST_NAME_MIN_LENGTH, max = Constants.OWNER_FIRST_NAME_MAX_LENGTH, message = "Length of 'FirstName' must be between {min} and {max}.")
public @interface ValidFirstName {
    String message() default "OWNER_FIRST_NAME: String format invalid!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
