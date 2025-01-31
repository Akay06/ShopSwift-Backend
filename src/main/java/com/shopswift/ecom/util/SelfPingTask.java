package com.shopswift.ecom.util;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SelfPingTask {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String selfUrl = "https://shopswift-19of.onrender.com/shopswift/actuator/health";

    @Scheduled(fixedRate = 30000) // Every 30 seconds
    public void pingSelf() {
        try {
            restTemplate.getForObject(selfUrl, String.class);
        } catch (Exception e) {
            System.err.println("Failed to ping self: " + e.getMessage());
        }
    }
}

