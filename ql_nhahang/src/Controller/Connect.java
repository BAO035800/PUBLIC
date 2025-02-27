package Controller;
import java.sql.*;

public class Connect {
    public static void main(String[] args) {
        String user = "root";
        String pass = "035800";
        String url = "jdbc:mysql://127.0.0.1:3306/ql_nhahang";
        String driver = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Kết nối thành công");
        } catch (Exception e) {
            System.out.println("Kết nối thất bại");
            e.printStackTrace();
        }
    }
}
