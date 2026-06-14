package com.energy;

import java.time.LocalDateTime;

public class EnergyReading {
    private int id;
    private String deviceId;
    private double voltage;
    private double current;
    private double power;
    private double energy;
    private LocalDateTime timestamp;

    public EnergyReading( String deviceId, double voltage, double current, double power, double energy) {
        this.deviceId = deviceId;
        this.voltage = voltage;
        this.current = current;
        this.power = power;
        this.energy = energy;

    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getDeviceId(){
        return deviceId;
    }
    public void setDeviceId(String deviceId){
        this.deviceId=deviceId;
    }
    public double getVoltage(){
        return voltage;
    }
    public void setVoltage(double voltage){
        this.voltage=voltage;
    }
    public double getCurrent(){
        return current;
    }
    public void setCurrent(double current){
        this.current=current;
    }
    public double getPower(){
        return power;
    }
    public void setPower(double power){
        this.power=power;
    }
    public double getEnergy(){
        return energy;
    }
    public void setEnergy(double energy){
        this.energy=energy;
    }
    public LocalDateTime getTimestamp(){
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp){
        this.timestamp=timestamp;
    }

    @Override
    public String toString(){
        return "EnergyReading{" +
                "id=" + id +
                ", deviceId='" + deviceId + '\'' +
                ", voltage=" + voltage +
                ", current=" + current +
                ", power=" + power +
                ", energy=" + energy +
                ", timestamp=" + timestamp +
                '}';
    }
}
