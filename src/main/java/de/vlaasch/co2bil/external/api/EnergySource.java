package de.vlaasch.co2bil.external.api;

import java.math.BigDecimal;

public record EnergySource(
    String energySourceId,
    String scopeId,
    String name,
    BigDecimal conversionFactor,
    BigDecimal emissionFactor
) {

}