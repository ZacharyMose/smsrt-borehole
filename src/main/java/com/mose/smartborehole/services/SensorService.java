package com.mose.smartborehole.services;

import com.mose.smartborehole.dto.SensorData;
import com.mose.smartborehole.entities.Boreholes;
import com.mose.smartborehole.entities.Sensors;
import com.mose.smartborehole.repositories.BoreholeRepository;
import com.mose.smartborehole.repositories.SensorRepository;
import com.mose.smartborehole.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SensorService {
    private final SensorRepository sensorRepository;
    private final BoreholeRepository boreholeRepository;
    private final AlertService alertService;

    public void receiveSensorData(SensorData dto) {
        Boreholes borehole = boreholeRepository.findById(dto.getBoreholeId())
                .orElseThrow(() -> new RuntimeException("Borehole not found"));

        Sensors sensor = new Sensors();
        sensor.setWaterLevel(dto.getWaterLevel());
        sensor.setDistance(dto.getDistance());
        sensor.setPumpStatus(dto.getPumpStatus());
        sensor.setTimestamp(LocalDateTime.now());
        sensor.setBorehole(borehole);

        sensorRepository.save(sensor);
    }

    public List<Sensors> getLatestForBorehole(UUID boreholeId) {
        return sensorRepository.findTopByBoreholeIdOrderByTimestampDesc(boreholeId);
    }
}
