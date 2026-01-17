package com.krlsystem.dao;

import com.krlsystem.util.DBConnection;

import java.sql.*;

public class TariffDAO {
    public double getBaseFare() {
        return getTariffValue("base_fare");
    }

    public double getFarePerKm() {
        return getTariffValue("fare_per_km");
    }

    private double getTariffValue(String column) {
        String sql = "SELECT " + column + " FROM tariffs WHERE id = 1";
        try (Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next())
                return rs.getDouble(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean updateTariffs(double baseFare, double farePerKm) {
        String sql = "UPDATE tariffs SET base_fare = ?, fare_per_km = ? WHERE id = 1";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, baseFare);
            stmt.setDouble(2, farePerKm);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
