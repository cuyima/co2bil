package de.vlaasch.co2bil.external.energysource;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import de.vlaasch.co2bil.external.api.EnergySource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class EnergySourcesClient {

    private final EnergySourcesProperties externalApiProperties;
    private final RestTemplate restTemplate;

    public EnergySource[] fetchEnergySources() {
        String energySourcesApiUrl = externalApiProperties.getEnergySourcesApiUrl();
        log.info("Getting energy sources from external API: {} ", energySourcesApiUrl);
        return restTemplate.getForObject(energySourcesApiUrl, EnergySource[].class);
    }
}