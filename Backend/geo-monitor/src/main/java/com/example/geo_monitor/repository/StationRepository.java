package com.example.geo_monitor.repository;

import com.example.geo_monitor.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StationRepository extends JpaRepository<Station, UUID> {
    
}
