package com.mose.smartborehole.repositories;

import com.mose.smartborehole.entities.Boreholes;
import com.mose.smartborehole.entities.MaintenanceLogs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MaintenanceRepository extends JpaRepository<MaintenanceLogs, UUID> {
    List<MaintenanceLogs> findByBoreholeId(UUID boreholeId);
}
