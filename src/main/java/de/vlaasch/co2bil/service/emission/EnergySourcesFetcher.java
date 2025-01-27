package de.vlaasch.co2bil.service.emission;

import java.util.List;

import org.springframework.stereotype.Component;

import de.vlaasch.co2bil.exceptions.RemoteServiceException;
import de.vlaasch.co2bil.external.api.EnergySource;
import de.vlaasch.co2bil.external.energysource.EnergySourcesClient;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EnergySourcesFetcher {

    private final EnergySourcesClient energySourcesClient;

    public List<EnergySource> fetchEnergySources() {
        EnergySource[] sources = energySourcesClient.fetchEnergySources();

        if (sources == null || sources.length == 0) {
            throw new RemoteServiceException("No energy sources found");
        }

        return List.of(sources);
    }
}
