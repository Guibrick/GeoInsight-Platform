package com.example.geo_monitor.service;

import com.example.geo_monitor.model.Station;
import com.example.geo_monitor.repository.StationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StationService {

    private final StationRepository repository;

    public StationService(StationRepository repository) {
        this.repository = repository;
    }

    public List<Station> getAllStations() {
        return repository.findAll();
    }

    public Station getStationById(UUID uniqueId) {
        return repository.findById(uniqueId).orElse(null);
    }

    public Station createStation(Station station) {
        return repository.save(station);
    }
}