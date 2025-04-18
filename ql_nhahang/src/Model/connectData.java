package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

public class connectData {
    // Thông tin kết nối CSDL
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/ql_nhahang";
    private static final String USER = "root";
    private static final String PASSWORD = "035800";

    // Phương thức kết nối database
    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Phương thức load dữ liệu vào JTable
    public static void loadData(DefaultTableModel model, String query, String[] customColumnNames) {
        if (model == null) {
            throw new NullPointerException("❌ DefaultTableModel chưa được khởi tạo!");
        }
        model.setRowCount(0); // Xóa dữ liệu cũ
    
        try (Connection conn = connect()) {
            if (conn == null) {
                System.err.println("⚠ Không thể tải dữ liệu vì kết nối CSDL thất bại!");
                return;
            }

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {

                int columnCount = rs.getMetaData().getColumnCount();
                Object[] columnNames = new Object[columnCount];

                // Sử dụng tên cột tùy chỉnh nếu hợp lệ, ngược lại lấy từ database
                if (customColumnNames != null && customColumnNames.length == columnCount) {
                    columnNames = customColumnNames;
                } else {
                    for (int i = 0; i < columnCount; i++) {
                        columnNames[i] = rs.getMetaData().getColumnName(i + 1);
                    }
                }
                model.setColumnIdentifiers(columnNames);

                // Đổ dữ liệu vào bảng
                while (rs.next()) {
                    Object[] rowData = new Object[columnCount];
                    for (int i = 0; i < columnCount; i++) {
                        rowData[i] = rs.getObject(i + 1);
                    }
                    model.addRow(rowData);
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi tải dữ liệu: " + e.getMessage());
        }
    }
    public static List<Map<String, Object>> loadDataFromTable(String tableName) {
        List<Map<String, Object>> data = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName; // Lấy tất cả dữ liệu từ bảng

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Lấy thông tin cột
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Duyệt qua tất cả các dòng dữ liệu
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValue = rs.getObject(i);
                    row.put(columnName, columnValue);
                }
                data.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}
