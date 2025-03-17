package controller;
import java.sql.*;
import javax.swing.JOptionPane;
import model.Connect;
public interface KTNhanSu {
    default boolean kiemTraNhanVienTonTai(String maNV) {
        String sql = "SELECT COUNT(*) FROM nhanvien WHERE MaNhanVien = ?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maNV);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi kiểm tra nhân viên: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}
