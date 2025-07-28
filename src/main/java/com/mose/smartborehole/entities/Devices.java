package com.mose.smartborehole.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Devices {
    @Id
    @GeneratedValue
    private UUID id;

    private String serialNumber;
    private String firmware;
    private String networkType; // GSM, WiFi

    private LocalDateTime lastSeen;

    @ManyToOne
    @JoinColumn(name = "borehole_id")
    private Boreholes borehole;


    @OneToMany(mappedBy = "device")
    private List<Alerts> alerts;
}
