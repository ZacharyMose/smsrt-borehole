package com.mose.smartborehole.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AiPredictions {
    @Id @GeneratedValue
    private Long id;

    private String predictionType; // water_forecast, failure_risk
    private Double predictedValue;
    private LocalDateTime predictionTime;
    private LocalDateTime generatedAt;

    @ManyToOne
    @JoinColumn(name = "borehole_id")
    private Boreholes borehole;
}
