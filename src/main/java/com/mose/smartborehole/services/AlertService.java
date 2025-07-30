package com.mose.smartborehole.services;

import com.mose.smartborehole.entities.Alerts;
import com.mose.smartborehole.entities.Users;
import com.mose.smartborehole.repositories.AlertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AlertService {

    @Autowired
    private AlertRepository alertsRepository;

    public Alerts createAlert(Users user, String title, String message) {
        Alerts alert = new Alerts(title, message, user);
        return alertsRepository.save(alert);
    }

    public List<Alerts> getUserAlerts(Users user) {
        return alertsRepository.findByUserOrderByCreatedAtDesc(user);
    }

    public void markAsRead(Long alertId) {
        Alerts alert = alertsRepository.findById(alertId)
                .orElseThrow(() -> new RuntimeException("Alert not found"));
        alert.setRead(true);
        alertsRepository.save(alert);
    }
}
