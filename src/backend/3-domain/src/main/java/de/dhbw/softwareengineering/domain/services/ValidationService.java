package de.dhbw.softwareengineering.domain.services;

import jakarta.validation.*;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ValidationService {
    public <T> void validate(T entity){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(entity);

        if(violations.isEmpty()){
            return;
        }

        throw new ConstraintViolationException(violations);
    }

}
