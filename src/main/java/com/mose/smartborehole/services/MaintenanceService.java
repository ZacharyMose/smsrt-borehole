package com.mose.smartborehole.services;

import com.mose.smartborehole.dto.MaintenanceLogDTO;
import com.mose.smartborehole.entities.Boreholes;
import com.mose.smartborehole.entities.MaintenanceLogs;
import com.mose.smartborehole.entities.Users;
import com.mose.smartborehole.repositories.BoreholeRepository;
import com.mose.smartborehole.repositories.MaintenanceRepository;
import com.mose.smartborehole.repositories.UserRepository;
import com.mose.smartborehole.response.MaintenanceLogsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MaintenanceService {

    private final MaintenanceRepository logRepository;
    private final BoreholeRepository boreholeRepository;
    private final UserRepository userRepository;

    public MaintenanceLogsResponse create(MaintenanceLogDTO request, Authentication auth) {
        String email = auth.getName();
        Users technician = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Technician not found"));

        Boreholes borehole = boreholeRepository.findById(request.getBoreholeId())
                .orElseThrow(() -> new RuntimeException("Borehole not found"));

        MaintenanceLogs log = new MaintenanceLogs();
        log.setDescription(request.getDescription());
        log.setBorehole(borehole);
        log.setTechnician(technician);
        log.setTimestamp(LocalDateTime.now());

        return mapToDTO(logRepository.save(log));
    }

    public List<MaintenanceLogsResponse> getAll() {
        return logRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<MaintenanceLogsResponse> getByBorehole(UUID id) {
        return logRepository.findByBoreholeId(id).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private MaintenanceLogsResponse mapToDTO(MaintenanceLogs log) {
        return new MaintenanceLogsResponse(
                log.getId(),
                log.getDescription(),
                log.getTechnician().getUsername(),
                log.getTimestamp(),
                log.getBorehole().getName()
        );
    }

}
