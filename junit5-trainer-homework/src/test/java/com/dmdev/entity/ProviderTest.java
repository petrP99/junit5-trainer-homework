package com.dmdev.entity;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProviderTest {
    @Test
    void findByNameOpt() {
        String validName = "apple";

        Optional<Provider> providerOpt = Provider.findByNameOpt(validName);

        assertThat(providerOpt.get().name().equalsIgnoreCase(validName));
    }

    @Test
    void shouldNotfindByNameOpt() {
        String validName = "provider";

        Optional<Provider> providerOpt = Provider.findByNameOpt(validName);

        assertThat(providerOpt).isEmpty();
    }
}