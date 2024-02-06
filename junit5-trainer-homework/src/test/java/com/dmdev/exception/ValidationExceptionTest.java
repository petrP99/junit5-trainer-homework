package com.dmdev.exception;

import com.dmdev.validator.Error;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ValidationExceptionTest {

    @Test
    void getErrors() {
        List<Error> errors = new ArrayList<>();

        errors.add(Error.of(100, "Error message 100"));
        errors.add(Error.of(101, "Error message 101"));
        ValidationException validationException = new ValidationException(errors);

        assertThat(validationException.getErrors()).isEqualTo(errors);
    }
}