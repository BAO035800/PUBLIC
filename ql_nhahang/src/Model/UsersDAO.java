package model;

import java.util.HashMap;
import java.util.Map;

public class UsersDAO {
    // Danh sách tài khoản cố định và vai trò của chúng
    private static final Map<String, User> fixedAccounts = new HashMap<>();

    // Lớp nội bộ để lưu thông tin tài khoản cố định
    private static class User {
        String password;
        String role;

        User(String password, String role) {
            this.password = password;
            this.role = role;
        }
    }

    static {
        fixedAccounts.put("admin", new User("admin", "Admin"));
        fixedAccounts.put("BanHang", new User("BanHang", "BanHang"));
        fixedAccounts.put("Bep", new User("Bep", "Bep"));
        fixedAccounts.put("1", new User("1", "Admin"));
    }

    // Phương thức đăng nhập trả về vai trò của người dùng
    public static String loginUser(String username, String password) {
        // Chỉ kiểm tra tài khoản cố định
        if (fixedAccounts.containsKey(username) && fixedAccounts.get(username).password.equals(password)) {
            return fixedAccounts.get(username).role; // Trả về vai trò
        }
        return null; // Đăng nhập thất bại
    }
}