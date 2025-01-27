package de.vlaasch.co2bil.testutil;

import java.math.BigDecimal;
import java.util.List;

import de.vlaasch.co2bil.external.api.EnergySource;
import de.vlaasch.co2bil.web.api.Co2Balance;
import de.vlaasch.co2bil.web.api.EnergyUsageEntry;

public class EnergySourcesFixture {

    public static List<EnergySource> energySourcesFixture() {
        return List.of(
                new EnergySource(
                        "1000",
                        "SCOPE_2_1",
                        "Strommix (Deutschland)",
                        BigDecimal.valueOf(1),
                        BigDecimal.valueOf(0.442)),
                new EnergySource(
                        "1001",
                        "SCOPE_2_1",
                        "Ökostrom (erneuerbar, eingekauft)",
                        BigDecimal.valueOf(1),
                        BigDecimal.valueOf(0)));
    }

    public static List<Co2Balance> co2BalanceFixture() {
        return List.of(
                new Co2Balance(
                        "Heizöl leicht (Standort Berlin)",
                        BigDecimal.valueOf(228.47),
                        BigDecimal.valueOf(0.06)),
                new Co2Balance(
                        "Ökostrom (erneuerbar, eingekauft) (Standort Hamburg)",
                        BigDecimal.valueOf(20.12),
                        BigDecimal.valueOf(0.00)));
    }

    public static List<EnergyUsageEntry> energyUsageEntriesFixture() {
        return List.of(
                new EnergyUsageEntry(
                        "2001",
                        "Standort Berlin",
                        BigDecimal.valueOf(20.66666),
                        null),
                new EnergyUsageEntry(
                        "1001",
                        "Standort Hamburg",
                        BigDecimal.valueOf(20.123),
                        null));
    }
}
