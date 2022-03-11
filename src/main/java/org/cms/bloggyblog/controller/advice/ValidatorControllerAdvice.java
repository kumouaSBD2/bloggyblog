package org.cms.bloggyblog.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.cms.bloggyblog.validator.ValidationErrorResponse;
import org.cms.bloggyblog.validator.Violation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ValidatorControllerAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onConstraintValidationException(ConstraintViolationException e) {
        log.info(" inside onConstraintValidationException  " + e.getMessage());
        ValidationErrorResponse error = new ValidationErrorResponse();
        error.getViolations().addAll(e.getConstraintViolations().stream().map(
                        violation -> new Violation(violation.getPropertyPath().toString(), violation.getMessage()))
                .collect(Collectors.toList()));
        return error;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        log.info(" inside onConstraintValidationException  " + e.getMessage());
        ValidationErrorResponse error = new ValidationErrorResponse();
        error.getViolations().addAll(e.getBindingResult().getFieldErrors().stream()
                .map(violation -> new Violation(violation.getField(), violation.getDefaultMessage()))
                .collect(Collectors.toList()));
        return error;
    }



}
