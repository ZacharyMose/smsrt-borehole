package com.mose.smartborehole.repositories;

import com.mose.smartborehole.entities.Devices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Devices, Long> {
}
