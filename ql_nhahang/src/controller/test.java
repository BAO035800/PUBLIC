package controller;
import java.sql.*;

public class test {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ql_nhahang", "root", "035800");
            System.out.println("Kết nối thành công");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Kết nối thất bại");
        }
    }
}

