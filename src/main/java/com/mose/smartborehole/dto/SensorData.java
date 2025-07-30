package com.mose.smartborehole.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SensorData {
    private Float waterLevel;
    private Float distance;
    private String pumpStatus;
    private UUID boreholeId;
}
