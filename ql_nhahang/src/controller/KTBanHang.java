package controller;
import java.sql.*;
import javax.swing.JOptionPane;
import model.Connect;
public interface KTBanHang {
    default boolean kiemTraBanTonTai(String soBan) {
        String sql = "SELECT COUNT(*) FROM ban WHERE SoBan = ?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, soBan);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi kiểm tra bàn: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
}
