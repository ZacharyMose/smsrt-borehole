package com.mose.smartborehole.repositories;

import com.mose.smartborehole.entities.Alerts;
import com.mose.smartborehole.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AlertRepository extends JpaRepository<Alerts, UUID> {
    List<Alerts> findByBoreholeId(UUID boreholeId);
}
