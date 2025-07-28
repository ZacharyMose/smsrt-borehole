package com.mose.smartborehole.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertService {

    private final EmailService emailService;

    // Thresholds
    private static final double WATER_LEVEL_THRESHOLD = 20.0;  // cm
    private static final String PUMP_STATUS_THRESHOLD = "OFF";

    // Static recipients for now — you can make this dynamic based on borehole later
    private final List<String> alertRecipients = List.of("mosezachary198@gmail.com", "technician@example.com");

    public void checkAndSendAlerts(String boreholeName, double waterLevel, String pumpStatus) {
        String subject = "⚠ Borehole Alert: " + boreholeName;
        StringBuilder body = new StringBuilder();

        boolean alertTriggered = false;

        if (waterLevel < WATER_LEVEL_THRESHOLD) {
            body.append("• Water level is too low: ").append(waterLevel).append(" cm.<br>");
            alertTriggered = true;
        }

        if (PUMP_STATUS_THRESHOLD.equalsIgnoreCase(pumpStatus)) {
            body.append("• Pump is currently OFF.<br>");
            alertTriggered = true;
        }

        if (alertTriggered) {
            body.insert(0, "<h2>Borehole Alert Triggered!</h2>");
            body.append("<br><i>Respond immediately.</i>");
            emailService.sendAlertEmail(alertRecipients, subject, body.toString());
        }
    }
}
