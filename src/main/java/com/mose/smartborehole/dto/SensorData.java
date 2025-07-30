package com.mose.smartborehole.dto;

import lombok.Data;

@Data
public class SensorData {
    private Double distance;
    private Double waterLevel;
    private String pumpStatus;
}
