package de.vlaasch.co2bil.web.api;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotEmpty;

@Validated
public record EnergyUsageRequest(
    @NotEmpty
    List<EnergyUsageEntry> entries
) {

}
