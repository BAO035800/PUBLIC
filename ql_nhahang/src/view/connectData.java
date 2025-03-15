package view;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public interface connectData {
    // Thông tin kết nối CSDL
    String URL = "jdbc:mysql://127.0.0.1:3306/ql_nhahang";
    String USER = "root";
    String PASSWORD = "035800";

    // Phương thức kết nối database
    default Connection connect() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    // Phương thức load dữ liệu vào JTable cụ thể
    default void loadData(DefaultTableModel model, String query, String[] customColumnNames) {
        model.setRowCount(0); // Xóa dữ liệu cũ
    
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
    
            int columnCount = rs.getMetaData().getColumnCount();
            Object[] columnNames = new Object[columnCount];
    
            // Sử dụng tên cột tùy chỉnh nếu có, ngược lại lấy từ database
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }   
    default void loadData(DefaultTableModel model, String query) {
        loadData(model, query, null); // Gọi lại phương thức có 3 tham số
    }
    
}
