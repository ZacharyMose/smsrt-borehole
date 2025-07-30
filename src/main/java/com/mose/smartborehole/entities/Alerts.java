package com.mose.smartborehole.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Alerts {
    @Id
    @GeneratedValue
    private UUID id;

    private String type; // e.g. LOW_WATER_LEVEL, TANK_EMPTY
    private String message;

    private UUID boreholeId;
    private LocalDateTime timestamp;
}
