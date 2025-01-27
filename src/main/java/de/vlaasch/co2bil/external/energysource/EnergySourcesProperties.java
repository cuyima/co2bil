package de.vlaasch.co2bil.external.energysource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
public class EnergySourcesProperties {

    private final String energySourcesApiUrl;

    public EnergySourcesProperties(@Value("${external.api.energySourcesApiUrl}") String energySourcesApiUrl) {
        this.energySourcesApiUrl = energySourcesApiUrl;
    }

}