package com.krlsystem.dao;

import com.krlsystem.model.Station;
import com.krlsystem.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StationDAO {
    public List<Station> getAllStations() {
        List<Station> stations = new ArrayList<>();
        String sql = "SELECT * FROM stations ORDER BY distance_from_start ASC";
        try (Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                stations.add(new Station(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getDouble("distance_from_start")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stations;
    }

    public boolean addStation(Station s) {
        String sql = "INSERT INTO stations (id, name, distance_from_start) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, s.getId());
            stmt.setString(2, s.getName());
            stmt.setDouble(3, s.getDistanceFromStart());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateStation(Station s) {
        String sql = "UPDATE stations SET name = ?, distance_from_start = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, s.getName());
            stmt.setDouble(2, s.getDistanceFromStart());
            stmt.setString(3, s.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteStation(String id) {
        String sql = "DELETE FROM stations WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
