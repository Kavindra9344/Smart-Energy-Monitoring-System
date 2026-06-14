package com.energy;

public class AlertEngine {
    private static final double POWER_THRESHOLD = 1000.0; 
    private static final double VOLTAGE_THRESHOLD = 250.0; 
    private static final double CURRENT_THRESHOLD = 10.0;  

    private final EnergyDAO energyDAO;

    public AlertEngine() {
        this.energyDAO=new EnergyDAO();
    }

    public void checkReading(EnergyReading reading){
        if(reading.getPower()>POWER_THRESHOLD){
            String message = "HIGH POWER ALERT! Power: " +reading.getPower() + "W exceeds " + POWER_THRESHOLD + "W";
            System.out.println("⚠️ ALERT: " + message);
            energyDAO.saveAlert(reading.getDeviceId(),message,reading.getPower());
        }
        if(reading.getVoltage()>VOLTAGE_THRESHOLD){
            String message = "HIGH VOLTAGE ALERT! Voltage: " + reading.getVoltage() + "V exceeds " + VOLTAGE_THRESHOLD + "V";
            System.out.println("⚠️ ALERT: " + message);
            energyDAO.saveAlert(reading.getDeviceId(), message, reading.getPower());
        }


        if(reading.getCurrent()>CURRENT_THRESHOLD) {
            String message = "HIGH CURRENT ALERT! Current: " + reading.getCurrent() + "A exceeds " + CURRENT_THRESHOLD + "A";
            System.out.println("⚠️ ALERT: " + message);
            energyDAO.saveAlert(reading.getDeviceId(), message, reading.getPower());
        }
    }
}
