package de.vlaasch.co2bil.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.vlaasch.co2bil.external.api.EnergySource;
import de.vlaasch.co2bil.service.emission.Co2BalanceService;
import de.vlaasch.co2bil.service.emission.EnergySourcesFetcher;
import de.vlaasch.co2bil.web.api.Co2Balance;
import de.vlaasch.co2bil.web.api.EnergyUsageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/energy")
@RequiredArgsConstructor
public class EnergyController {

    private final Co2BalanceService co2BalanceService;
    private final EnergySourcesFetcher energySourcesFetcher;

    @PostMapping(value = "/balance")
    public ResponseEntity<List<Co2Balance>> calculateCo2Balance(@RequestBody EnergyUsageRequest request) {
        return ResponseEntity.ok(co2BalanceService.getCo2Balance(request));
    }

    @GetMapping(value = "/sources")
    public ResponseEntity<List<EnergySource>> fetchExternalEnergySources() {
        return ResponseEntity.ok(energySourcesFetcher.fetchEnergySources());
    }
}
