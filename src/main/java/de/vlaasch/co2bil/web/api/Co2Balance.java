package de.vlaasch.co2bil.web.api;

import java.math.BigDecimal;

public record Co2Balance(
    String label,
    BigDecimal energy,
    BigDecimal co2
) {

}
