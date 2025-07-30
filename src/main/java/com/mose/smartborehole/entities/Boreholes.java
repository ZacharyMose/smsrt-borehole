package com.mose.smartborehole.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Boreholes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String location;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Users admin;

    @ManyToMany
    @JoinTable(
            name = "borehole_technicians",
            joinColumns = @JoinColumn(name = "borehole_id"),
            inverseJoinColumns = @JoinColumn(name = "technician_id")
    )
    private List<Users> technicians;
}
