package com.mose.smartborehole.services;

import com.mose.smartborehole.dto.SensorData;
import com.mose.smartborehole.entities.Sensors;
import com.mose.smartborehole.repositories.SensorRepository;
import com.mose.smartborehole.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SensorService {
    private final SensorRepository repository;
    private final UserRepository userRepository;

    public void save(SensorData dto) {
        Sensors data = new Sensors();
        data.setDistance(dto.getDistance());
        data.setTimestamp(LocalDateTime.now());
        data.setWaterLevel(dto.getWaterLevel());
        data.setPumpStatus(dto.getPumpStatus());

        repository.save(data);
    }

    public List<Sensors> getAll() {
        return repository.findAll();
    }
}
