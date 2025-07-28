package com.mose.smartborehole.controllers;

import com.mose.smartborehole.services.AlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alerts")
@RequiredArgsConstructor
public class AlertController {

    private final AlertService alertService;

    @PostMapping("/trigger")
    public String triggerAlert(@RequestParam String boreholeName,
                               @RequestParam double waterLevel,
                               @RequestParam String pumpStatus) {
        alertService.checkAndSendAlerts(boreholeName, waterLevel, pumpStatus);
        return "Alert check complete.";
    }
}
