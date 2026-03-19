package com.example.geo_monitor.controller;

import com.example.geo_monitor.model.Station;
import com.example.geo_monitor.service.StationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/stations")
public class StationController {

    private final StationService service;

    public StationController(StationService service) {
        this.service = service;
    }

    @GetMapping
    public List<Station> getAllStations() {
        return service.getAllStations();
    }

    @GetMapping("/{uniqueId}")
    public Station getStationByUniqueId(@PathVariable UUID uniqueId) {
        return service.getStationById(uniqueId);
    }

    @PostMapping
    public Station createStation(@RequestBody Station station) {
        return service.createStation(station);
    }
}