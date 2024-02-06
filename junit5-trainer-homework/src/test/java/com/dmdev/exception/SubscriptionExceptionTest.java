package com.dmdev.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SubscriptionExceptionTest {

    @Test
    public void SubscriptionException() {
        String message = "Test error message";

        SubscriptionException subscriptionException = new SubscriptionException(message);

        assertThat(subscriptionException.getMessage()).isEqualTo("Test error message");

    }
}

