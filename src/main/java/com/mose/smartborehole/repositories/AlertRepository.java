package com.mose.smartborehole.repositories;

import com.mose.smartborehole.entities.Alerts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepository extends JpaRepository<Alerts, Long> {
}
