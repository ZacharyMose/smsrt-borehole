package com.mose.smartborehole.repositories;

import com.mose.smartborehole.entities.Sensors;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface SensorRepository extends JpaRepository<Sensors, UUID> {
}
