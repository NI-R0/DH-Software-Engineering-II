package de.dhbw.softwareengineering.plugins.REST;

import de.dhbw.softwareengineering.exceptions.ObjectNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.Builder;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.List;
import java.util.Set;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    @Builder
    private record InvalidatedParams (String cause, String attribute) {}

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ProblemDetail> handleIllegalArgumentException(IllegalArgumentException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(BAD_REQUEST, "Illegal body argument.");
        problemDetail.setType(URI.create("InvalidArgument"));
        problemDetail.setTitle("Invalid Argument");
        problemDetail.setProperty("Message", e.getMessage());
        return ResponseEntity.badRequest().body(problemDetail);
    }

    @ExceptionHandler(ConstraintViolationException.class)
     protected ResponseEntity<ProblemDetail> handleConstraintViolationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> errors = e.getConstraintViolations();
        List<InvalidatedParams> validationResponse = errors.stream()
                .map(err -> InvalidatedParams.builder()
                        .cause(err.getMessage())
                        .attribute(err.getPropertyPath().toString())
                        .build()
                ).toList();

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(BAD_REQUEST, "Request validation failed (bean validation)");
        problemDetail.setTitle("Constraint Validation Failed");
        problemDetail.setType(URI.create("ConstraintViolation"));
        problemDetail.setProperty("invalidParameters", validationResponse);
        return ResponseEntity.badRequest().body(problemDetail);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();

        List<InvalidatedParams> validationResponse = errors.stream()
                .map(err -> InvalidatedParams.builder()
                        .cause(err.getDefaultMessage())
                        .attribute(err.getField())
                        .build()
                ).toList();

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(BAD_REQUEST, "Request validation failed (method annotation)");
        problemDetail.setTitle("Method Argument Validation Failed");
        problemDetail.setType(URI.create("MethodArgumentNotValid"));
        problemDetail.setProperty("invalidParameters", validationResponse);
        return ResponseEntity.badRequest().body(problemDetail);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    protected ResponseEntity<ProblemDetail> handleObjectNotFoundException(ObjectNotFoundException e){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(NOT_FOUND, "Object not found");
        problemDetail.setType(URI.create("ObjectNotFound"));
        problemDetail.setTitle("Object Not Found");
        problemDetail.setProperty("Message", e.getMessage());
        return ResponseEntity.badRequest().body(problemDetail);
    }

}
