package de.vlaasch.co2bil.service.emission;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import de.vlaasch.co2bil.exceptions.NotFoundException;
import de.vlaasch.co2bil.external.api.EnergySource;
import de.vlaasch.co2bil.web.api.Co2Balance;
import de.vlaasch.co2bil.web.api.EnergyUsageEntry;

@Component
class Co2BalanceTransformer {

    public Co2Balance transform(EnergyUsageEntry entry, List<EnergySource> energySources) {
        EnergySource matchingSource = findEnergySource(entry, energySources);
        BigDecimal emissionFactor = getEmissionFactor(entry, matchingSource);

        BigDecimal energy = entry.consumption().multiply(matchingSource.conversionFactor()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal co2 = energy.multiply(emissionFactor).divide(BigDecimal.valueOf(1000))
                .setScale(2, RoundingMode.HALF_UP);

        return new Co2Balance(
                String.format("%s (%s)", matchingSource.name(), entry.description()),
                energy,
                co2
        );
    }

    private EnergySource findEnergySource(EnergyUsageEntry entry, List<EnergySource> energySources) {
        return energySources.stream()
                .filter(src -> entry.id().equals(src.energySourceId()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(
                        String.format("Could not find a matching energy source for id %s.", entry.id())));
    }

    private BigDecimal getEmissionFactor(EnergyUsageEntry entry, EnergySource source) {
        return Optional.ofNullable(entry.emissionFactor()).orElse(source.emissionFactor());
    }
}