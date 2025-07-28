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
public class Sensors {
    @Id
    @GeneratedValue
    private UUID id;

    private Double distance;
    private Double vibration;
    private LocalDateTime timestamp;

}
