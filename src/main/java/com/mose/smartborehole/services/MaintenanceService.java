package com.mose.smartborehole.services;

import com.mose.smartborehole.dto.MaintenanceLogDTO;
import com.mose.smartborehole.entities.Boreholes;
import com.mose.smartborehole.entities.MaintenanceLogs;
import com.mose.smartborehole.entities.Users;
import com.mose.smartborehole.repositories.BoreholeRepository;
import com.mose.smartborehole.repositories.MaintenanceRepository;
import com.mose.smartborehole.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MaintenanceService {
    private final MaintenanceRepository logRepository;
    private final BoreholeRepository boreholeRepository;
    private final UserRepository userRepository;

    public void createLog(MaintenanceLogDTO dto, Authentication auth) {
        Users user = userRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("User not found: " + auth.getName()));

        Boreholes borehole = boreholeRepository.findById(dto.getBoreholeId())
                .orElseThrow(() -> new RuntimeException("Borehole not found with ID: " + dto.getBoreholeId()));


        MaintenanceLogs log = MaintenanceLogs.builder()
                .description(dto.getDescription())
                .status(dto.getStatus())
                .date(dto.getDate())
                .borehole(borehole)
                .performedBy(user)
                .build();

        logRepository.save(log);
    }

    public List<MaintenanceLogs> getLogsForBorehole(UUID boreholeId) {
        Boreholes borehole = boreholeRepository.findById(boreholeId)
                .orElseThrow();
        return logRepository.findByBorehole(borehole);
    }
}
