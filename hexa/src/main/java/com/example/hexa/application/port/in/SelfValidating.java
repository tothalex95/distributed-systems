package com.example.hexa.application.port.in;

import lombok.Data;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class SelfValidating<T> {

    private Validator validator;

    public SelfValidating() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    protected void validateSelf() {
        Set<ConstraintViolation<T>> violations = validator.validate((T) this);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

}
