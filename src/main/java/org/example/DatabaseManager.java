package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/search_engine";
    private static final String DEFAULT_USER = "root";
    private static final String DEFAULT_PASSWORD = "";
    private static final String URL = getEnv("SEARCH_ENGINE_DB_URL", DEFAULT_URL);
    private static final String USER = getEnv("SEARCH_ENGINE_DB_USER", DEFAULT_USER);
    private static final String PASSWORD = getEnv("SEARCH_ENGINE_DB_PASSWORD", DEFAULT_PASSWORD);

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void savePage(String url, String title) {
        String query = "INSERT INTO pages (url, title) VALUES (?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, url);
            stmt.setString(2, title);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    public List<String> search(String keyword) {
        List<String> results = new ArrayList<>();
        String query = "SELECT url FROM pages WHERE title LIKE ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                results.add(rs.getString("url"));
            }
        } catch (SQLException e) {
            System.out.println("Search error: " + e.getMessage());
        }

        return results;
    }

    private static String getEnv(String key, String defaultValue) {
        String value = System.getenv(key);
        return value == null || value.trim().isEmpty() ? defaultValue : value.trim();
    }
}
