package com.krlsystem.dao;

import com.krlsystem.model.Station;
import com.krlsystem.model.Transaction;
import com.krlsystem.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {
    public boolean addTransaction(Transaction trx) {
        String sql = "INSERT INTO transactions (user_id, origin_station_id, destination_station_id, amount) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, trx.getUserId());
            stmt.setString(2, trx.getOrigin().getId());
            stmt.setString(3, trx.getDestination().getId());
            stmt.setDouble(4, trx.getAmount());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Transaction> getHistoryByUserId(int userId, List<Station> allStations) {
        List<Transaction> history = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE user_id = ? ORDER BY timestamp DESC";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String originId = rs.getString("origin_station_id");
                String destId = rs.getString("destination_station_id");
                Station origin = findStation(originId, allStations);
                Station dest = findStation(destId, allStations);
                history.add(new Transaction(
                        rs.getInt("id"),
                        userId,
                        origin,
                        dest,
                        rs.getDouble("amount"),
                        rs.getTimestamp("timestamp").toLocalDateTime()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return history;
    }

    public double getTotalRevenue() {
        String sql = "SELECT SUM(amount) FROM transactions";
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

    private Station findStation(String id, List<Station> stations) {
        for (Station s : stations)
            if (s.getId().equals(id))
                return s;
        return null;
    }

    public List<Transaction> getAllTransactions(List<Station> allStations) {
        List<Transaction> history = new ArrayList<>();
        String sql = "SELECT * FROM transactions ORDER BY timestamp DESC";
        try (Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String originId = rs.getString("origin_station_id");
                String destId = rs.getString("destination_station_id");
                Station origin = findStation(originId, allStations);
                Station dest = findStation(destId, allStations);
                history.add(new Transaction(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        origin,
                        dest,
                        rs.getDouble("amount"),
                        rs.getTimestamp("timestamp").toLocalDateTime()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return history;
    }
}
