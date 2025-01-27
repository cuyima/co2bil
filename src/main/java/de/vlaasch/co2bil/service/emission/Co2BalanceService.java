package de.vlaasch.co2bil.service.emission;

import java.util.List;

import org.springframework.stereotype.Service;

import de.vlaasch.co2bil.external.api.EnergySource;
import de.vlaasch.co2bil.web.api.Co2Balance;
import de.vlaasch.co2bil.web.api.EnergyUsageEntry;
import de.vlaasch.co2bil.web.api.EnergyUsageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class Co2BalanceService {

    private final EnergySourcesFetcher energySourcesFetcher;
    private final Co2BalanceTransformer co2BalanceTransformer;

    public List<Co2Balance> getCo2Balance(EnergyUsageRequest request) {
        List<EnergySource> energySources = energySourcesFetcher.fetchEnergySources();
        List<EnergyUsageEntry> entries = request.entries();
        log.debug("Start calculating CO2 emissions of {} entries.", entries.size());

        return entries.stream()
                .map(entry -> co2BalanceTransformer.transform(entry, energySources))
                .toList();
    }
}
