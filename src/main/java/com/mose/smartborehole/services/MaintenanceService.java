package com.mose.smartborehole.services;

import com.mose.smartborehole.dto.MaintenanceLogDTO;
import com.mose.smartborehole.dto.MaintenanceLogRequest;
import com.mose.smartborehole.entities.Boreholes;
import com.mose.smartborehole.entities.MaintenanceLogs;
import com.mose.smartborehole.entities.Users;
import com.mose.smartborehole.repositories.BoreholeRepository;
import com.mose.smartborehole.repositories.MaintenanceRepository;
import com.mose.smartborehole.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MaintenanceService {
    private final MaintenanceRepository logsRepository;
    private final BoreholeRepository boreholesRepository;
    private final UserRepository usersRepository;

    public MaintenanceLogs createLog(MaintenanceLogRequest dto) {
        var borehole = boreholesRepository.findById(dto.boreholeId())
                .orElseThrow(() -> new RuntimeException("Borehole not found"));
        var user = usersRepository.findById(dto.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        var log = MaintenanceLogs.builder()
                .description(dto.description())
                .status(dto.status())
                .date(LocalDateTime.now())
                .borehole(borehole)
                .performedBy(user)
                .build();

        return logsRepository.save(log);
    }

    public List<MaintenanceLogs> getLogsByBorehole(UUID boreholeId) {
        return logsRepository.findByBorehole_Id(boreholeId);
    }

    public List<MaintenanceLogs> getAllLogs() {
        return logsRepository.findAll();
    }

}
