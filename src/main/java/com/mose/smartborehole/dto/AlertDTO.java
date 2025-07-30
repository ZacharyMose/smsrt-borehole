package com.mose.smartborehole.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class AlertDTO {
    private UUID id;
    private String type;
    private String message;
    private UUID boreholeId;
    private LocalDateTime timestamp;

}
