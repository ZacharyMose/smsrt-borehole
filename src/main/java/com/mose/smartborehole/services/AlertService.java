package com.mose.smartborehole.services;

import com.mose.smartborehole.dto.AlertDTO;
import com.mose.smartborehole.entities.Alerts;
import com.mose.smartborehole.entities.Boreholes;
import com.mose.smartborehole.entities.Sensors;
import com.mose.smartborehole.entities.Users;
import com.mose.smartborehole.repositories.AlertRepository;
import com.mose.smartborehole.repositories.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlertService {


    private final SensorRepository sensorReadingRepository;
    private final AlertRepository alertRepository;

    public List<AlertDTO> generateAlertsForBorehole(UUID boreholeId) {
        List<Sensors> latestReadingOpt = sensorReadingRepository.findTopByBoreholeIdOrderByTimestampDesc(boreholeId);
        if (latestReadingOpt.isEmpty()) {
            return Collections.emptyList();
        }

        Sensors reading = latestReadingOpt.get(5);
        List<Alerts> generatedAlerts = new ArrayList<>();

        double LOW_WATER_LEVEL = 30.0;
        double TANK_FULL = 10.0;
        double TANK_EMPTY = 60.0;

        if (reading.getWaterLevel() < LOW_WATER_LEVEL) {
            generatedAlerts.add(createAlert("LOW_WATER_LEVEL", "Water level is low: " + reading.getWaterLevel() + " cm", boreholeId));
        }

        if (reading.getDistance() < TANK_FULL) {
            generatedAlerts.add(createAlert("TANK_FULL", "Tank is full. Distance to surface: " + reading.getDistance() + " cm", boreholeId));
        } else if (reading.getWaterLevel() > TANK_EMPTY) {
            generatedAlerts.add(createAlert("TANK_EMPTY", "Tank is empty. Distance to surface: " + reading.getWaterLevel() + " cm", boreholeId));
        }

        if (reading.getDistance()  < TANK_FULL) {
            generatedAlerts.add(createAlert("PUMP_OVERFILL_RISK", "Pump running while tank is full!", boreholeId));
        }

        alertRepository.saveAll(generatedAlerts);

        return generatedAlerts.stream().map(this::toDto).collect(Collectors.toList());
    }

    private Alerts createAlert(String type, String message, UUID boreholeId) {
        return Alerts.builder()
                .type(type)
                .message(message)
                .boreholeId(boreholeId)
                .timestamp(LocalDateTime.now())
                .build();
    }

    private AlertDTO toDto(Alerts alert) {
        return AlertDTO.builder()
                .id(alert.getId())
                .type(alert.getType())
                .message(alert.getMessage())
                .timestamp(alert.getTimestamp())
                .boreholeId(alert.getBoreholeId())
                .build();
    }

    public List<AlertDTO> getAlertsForBorehole(UUID boreholeId) {
        return alertRepository.findByBoreholeId(boreholeId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

}
