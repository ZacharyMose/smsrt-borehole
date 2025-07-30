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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private Float waterLevel;
    private Float distance; // From ultrasonic
    private String pumpStatus;

    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "borehole_id")
    private Boreholes borehole;


}
