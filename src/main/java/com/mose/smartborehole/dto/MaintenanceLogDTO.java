package com.mose.smartborehole.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceLogDTO {
    private String description;
    private String status;
    private LocalDateTime date;
    private UUID boreholeId;
}
