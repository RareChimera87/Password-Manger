package DAO;

import Model.User;
import config.Config;
import config.DatabaseConnection;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class userDAO {
    public void insert(User user) throws Exception {
        String sql = """
                INSERT INTO users(username, password_hash, salt, created_at)
                VALUES (?, ?, ?, ?);
                """;

        try (Connection conn = DatabaseConnection.getConnection(Config.DB_PATH);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPasswordHash());
            pstmt.setString(3, user.getSalt());
            pstmt.setString(4, user.getCreatedAt().toString());

            pstmt.executeUpdate();
            System.out.println("User inserted successfully!");
        }

    }

    public List<User> getAllUsers() throws Exception {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = DatabaseConnection.getConnection(Config.DB_PATH);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                User u = new User("", "", "", java.time.LocalDateTime.now());
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setPasswordHash(rs.getString("password_hash"));
                u.setSAlt(rs.getString("salt"));
                u.setCreatedAt(java.time.LocalDateTime.parse(rs.getString("created_at")));
                users.add(u);
            }
        }

        return users;
    }

    public User getUserById(int id) throws Exception {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users where id = ?";
        try (Connection conn = DatabaseConnection.getConnection(Config.DB_PATH);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    User u = new User("", "", "", java.time.LocalDateTime.now());
                    u.setId(rs.getInt("id"));
                    u.setUsername(rs.getString("username"));
                    u.setPasswordHash(rs.getString("password_hash"));
                    u.setSAlt(rs.getString("salt"));
                    u.setCreatedAt(java.time.LocalDateTime.parse(rs.getString("created_at")));
                    return u;
                }
            }

        }
        return null;
    }

    public User getUserByUsername(String username) throws Exception {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users where  username = ?";
        try (Connection conn = DatabaseConnection.getConnection(Config.DB_PATH);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    User u = new User("", "", "", java.time.LocalDateTime.now());
                    u.setId(rs.getInt("id"));
                    u.setUsername(rs.getString("username"));
                    u.setPasswordHash(rs.getString("password_hash"));
                    u.setSAlt(rs.getString("salt"));
                    u.setCreatedAt(java.time.LocalDateTime.parse(rs.getString("created_at")));
                    return u;
                }
            }

        }
        return null;
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection(Config.DB_PATH);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
