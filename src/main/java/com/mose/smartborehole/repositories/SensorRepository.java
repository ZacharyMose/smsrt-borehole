package com.mose.smartborehole.repositories;

import com.mose.smartborehole.entities.Boreholes;
import com.mose.smartborehole.entities.Sensors;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface SensorRepository extends JpaRepository<Sensors, UUID> {
    List<Sensors> findTopByBoreholeIdOrderByTimestampDesc(UUID boreholeId);
}
