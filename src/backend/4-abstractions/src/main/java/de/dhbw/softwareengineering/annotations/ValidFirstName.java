package de.dhbw.softwareengineering.annotations;


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
@NotNull
@NotEmpty
@NotBlank
@Size(min = Constants.OWNER_FIRST_NAME_MIN_LENGTH)
@Size(max = Constants.OWNER_FIRST_NAME_MAX_LENGTH)
public @interface ValidFirstName {
    String message() default "OWNER_FIRST_NAME: String format invalid!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
