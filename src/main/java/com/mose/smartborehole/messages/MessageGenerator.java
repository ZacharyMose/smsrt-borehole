package com.mose.smartborehole.messages;

import org.springframework.stereotype.Service;

@Service
public class MessageGenerator {

    public String generateSubject(String boreholeName) {
        return "🚨 Alert - Borehole " + boreholeName;
    }

    public String generateWaterLevelAlert(String boreholeName, double value) {
        return String.format(
                "⚠️ Water level alert:\nBorehole '%s' has a critically low water level: %.2f meters.\nPlease take action immediately.",
                boreholeName, value
        );
    }

    public String generatePumpStatusAlert(String boreholeName, String status) {
        return String.format(
                "⚠️ Pump status alert:\nBorehole '%s' has an unexpected pump status: %s.\nTechnician check recommended.",
                boreholeName, status
        );
    }
}
