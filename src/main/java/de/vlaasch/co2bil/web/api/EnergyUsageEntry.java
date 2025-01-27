package de.vlaasch.co2bil.web.api;

import java.math.BigDecimal;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnergyUsageEntry(
                @NotBlank String id,
                @NotBlank String description,
                @NotNull @Digits(integer = 10, fraction = 5) BigDecimal consumption,
                @Digits(integer = 10, fraction = 5) BigDecimal emissionFactor) {

}
