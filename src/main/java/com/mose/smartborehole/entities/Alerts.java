package com.mose.smartborehole.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Alerts {
    @Id
    @GeneratedValue
    private Long id;

    private String alertType; // low_water, pump_failure, etc.
    private String message;
    private String sentVia;   // sms, email, in-app
    private boolean resolved;

    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Devices device;
}
