package com.mose.smartborehole.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "users")
public class Users implements UserDetails {
    @Id @GeneratedValue
    private UUID id;

    private String username;
    @Column(unique = true)
    private String email;
    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role; // ADMIN, TECHNICIAN,USER


    @ManyToMany(mappedBy = "technicians")
    private List<Boreholes> boreholes;

    @ManyToOne
    @JoinColumn(name = "borehole_id")
    private Boreholes borehole;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
