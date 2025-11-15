package DAO;


import config.Config;
import config.DatabaseConnection;
import Model.Credentials;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class credentialsDAO {
    public void insert(Credentials cred) throws SQLException {
        String sql = """
                INSERT INTO credentials(user_id, service_name, username, salt,password_encrypted,
                                        notes_encrypted, created_at, updated_at)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?);
                """;

        try (Connection conn = DatabaseConnection.getConnection(Config.DB_PATH);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, cred.getUser_id());
            pstmt.setString(2, cred.getServiceName());
            pstmt.setString(3, cred.getUsername());
            pstmt.setString(4, cred.getSalt());
            pstmt.setString(5, cred.getPasswordHash());
            pstmt.setString(6, cred.getNotasHash());
            pstmt.setString(7, cred.getCreatedAt().toString());
            pstmt.setString(8, cred.getUpdatedAt().toString());

            pstmt.executeUpdate();

            System.out.println("Servicio registrado correctamente");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<Credentials> getAllCredentials(int userid) throws Exception {
        List<Credentials> credentials = new ArrayList<>();
        String sql = "SELECT * FROM credentials WHERE user_id = " + userid;
        try (Connection conn = DatabaseConnection.getConnection(Config.DB_PATH);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Credentials u = new Credentials(userid, "", "", "", "", "", LocalDateTime.now(), LocalDateTime.now());
                u.setUser_id(rs.getInt("user_id"));
                u.setServiceName(rs.getString("service_name"));
                u.setUsername(rs.getString("username"));
                u.setPasswordHash(rs.getString("password_encrypted"));
                u.setNotasHash(rs.getString("notes_encrypted"));
                u.setCreatedAt(java.time.LocalDateTime.parse(rs.getString("created_at")));
                u.setUpdatedAt(java.time.LocalDateTime.parse(rs.getString("updated_at")));
                credentials.add(u);
            }
        }

        return credentials;
    }
}
