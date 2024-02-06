package com.dmdev.validator;

import com.dmdev.exception.ValidationException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ValidationResultTest {

    @Test
    void add() {
        List<Error> errors = createErrors();

        ValidationException validationException = new ValidationException(errors);

        assertThat(validationException.getErrors()).isEqualTo(errors);
    }

    @Test
    void hasErrors() {
        List<Error> errors = createErrors();

        assertFalse(errors.isEmpty());
    }

    public List<Error> createErrors() {
        List<Error> errors = new ArrayList<>();

        errors.add(Error.of(100, "Error message 100"));
        errors.add(Error.of(101, "Error message 101"));

        return errors;
    }
}