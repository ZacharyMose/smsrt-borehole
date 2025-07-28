package com.mose.smartborehole.repositories;

import com.mose.smartborehole.entities.Boreholes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BoreholeRepository extends JpaRepository<Boreholes, UUID> {
}
