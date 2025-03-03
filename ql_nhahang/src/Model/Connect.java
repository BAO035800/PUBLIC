package model;
import java.sql.*;

public class Connect {
    private static Connection conn = null;
    public static Connection getConnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ql_nhahang", "root", "035800");
            System.out.println("Kết nối thành công");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Kết nối thất bại");
        }
        return conn;
    }
}
