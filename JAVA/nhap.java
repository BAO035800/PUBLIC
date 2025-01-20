
import java.sql.*;

public class nhap {
    public static Connection getConnection() {
        String url = "jdbc:mysql://127.0.0.1:3306/?user=root";
        String username = "root";
        String password = "035800";
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Drive Manager Succesfully!");
            return conn;
        } catch (SQLException e) {
            System.out.println("Drive Manager Unsuccesfully!");
            e.printStackTrace();
            return null;
        }
    }

    public static void closeConnection(Connection conn) {
        try {
            conn.close();
            System.out.println("Đã đóng kết nối!");
        } catch (SQLException e) {
            System.out.println("Không thể đóng kết nối!");
            e.printStackTrace();
        }
    }

    public static void insertData(Connection conn) {
        if (conn == null) {
            System.out.println("Kết nối không hợp lệ!");
            return;
        }
        try {
            // Chọn database1
            Statement stsm = conn.createStatement();
            String useDB1 = "USE TEST";
            stsm.execute(useDB1);
            String delete = "UPDATE test.test1 SET tenSV='Tran Van A' WHERE maSV='1';";
            stsm.executeUpdate(delete);
            System.out.println("Làm việc với database1");
        } catch (Exception e) {
            System.out.println("Error làm việc với database1");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Kết nối đến cơ sở dữ liệu...");
        Connection c = getConnection();
        if (c != null) {
            insertData(c);
        }
    }
}
