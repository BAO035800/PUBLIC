package model;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class UsersDAO {
    // Danh sách tài khoản cố định
    private static final Map<String, String> fixedAccounts = new HashMap<>();

    static {
        fixedAccounts.put("admin", "admin");
        fixedAccounts.put("manager", "123456");
        fixedAccounts.put("staff", "staff123");
        fixedAccounts.put("1", "1");
    }

    public static boolean loginUser(String username, String password) {
        // Kiểm tra tài khoản cố định trước
        if (fixedAccounts.containsKey(username) && fixedAccounts.get(username).equals(password)) {
            return true; // Đăng nhập thành công
        }

        // Nếu không phải tài khoản cố định, kiểm tra database
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Đúng nếu có kết quả
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
