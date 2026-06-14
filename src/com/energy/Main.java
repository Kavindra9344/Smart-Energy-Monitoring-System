package com.energy;

import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException{
        EnergyDAO energyDAO = new EnergyDAO();
        AlertEngine alertEngine = new AlertEngine();
        Random random = new Random();
        System.out.println("Smart Energy Monitoring System Started...");
        System.out.println("Simulating ESP32 sensor data...\n");
        for (int i = 0; i < 10; i++) {
            double voltage = 210 + random.nextDouble() * 50;
            double current = 1 + random.nextDouble() * 10;
            double power = voltage * current;
            double energy = power * 0.001;
            EnergyReading reading = new EnergyReading(
                    "ESP32-Device-1",
                    Math.round(voltage * 100.0) / 100.0,
                    Math.round(current * 100.0) / 100.0,
                    Math.round(power * 100.0) / 100.0,
                    Math.round(energy * 100.0) / 100.0
            );

            energyDAO.saveReading(reading);
            alertEngine.checkReading(reading);
            Thread.sleep(100);
        }
        System.out.println("\nAll readings saved!");
        System.out.println("\nFetching all readings from database:");
        List<EnergyReading> readings = energyDAO.getAllReadings();
        readings.forEach(System.out::println);
    }
}