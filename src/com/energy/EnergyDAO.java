package com.energy;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnergyDAO {

    public void saveReading(EnergyReading reading){
        String sql = "INSERT INTO energy_readings (device_id, voltage, current, power, energy) VALUES (?, ?, ?, ?, ?)";
        try(Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, reading.getDeviceId());
            stmt.setDouble(2, reading.getVoltage());
            stmt.setDouble(3, reading.getCurrent());
            stmt.setDouble(4, reading.getPower());
            stmt.setDouble(5, reading.getEnergy());
            stmt.executeUpdate();
            System.out.println("Reading saved: " + reading);
        } catch(SQLException e){
            System.err.println("Error saving reading: " + e.getMessage());
        }
    }
    public List<EnergyReading> getAllReadings(){
        List<EnergyReading> readings = new ArrayList<>();
        String sql = "SELECT * FROM energy_readings ORDER BY timestamp DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                EnergyReading reading = new EnergyReading(
                        rs.getString("device_id"),
                        rs.getDouble("voltage"),
                        rs.getDouble("current"),
                        rs.getDouble("power"),
                        rs.getDouble("energy")
                );
                reading.setId(rs.getInt("id"));
                readings.add(reading);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching readings: " + e.getMessage());
        }
        return readings;
    }
    public List<EnergyReading> getReadingsByDevice(String deviceId){
        List<EnergyReading> readings = new ArrayList<>();
        String sql = "SELECT * FROM energy_readings WHERE device_id = ? ORDER BY timestamp DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, deviceId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                EnergyReading reading = new EnergyReading(
                        rs.getString("device_id"),
                        rs.getDouble("voltage"),
                        rs.getDouble("current"),
                        rs.getDouble("power"),
                        rs.getDouble("energy")
                );
                reading.setId(rs.getInt("id"));
                readings.add(reading);
            }
        } catch (SQLException e){
            System.err.println("Error fetching readings: " + e.getMessage());
        }
        return readings;
    }
    public void saveAlert(String deviceId, String message, double powerValue){
        String sql = "INSERT INTO alerts (device_id, message, power_value) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, deviceId);
            stmt.setString(2, message);
            stmt.setDouble(3, powerValue);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error saving alert: " + e.getMessage());
        }
    }
}