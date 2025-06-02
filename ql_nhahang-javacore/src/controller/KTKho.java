package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Connect;

public class KTKho {    
    public boolean kiemTraNguyenLieuTonTai(String maNguyenLieu) {
        String sql = "SELECT COUNT(*) FROM khonguyenlieu WHERE MaNguyenLieu = ?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maNguyenLieu);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi kiểm tra nguyên liệu: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean kiemTraNCCTonTai(String maNCC) {
        String sql = "SELECT COUNT(*) FROM nhacungcap WHERE MaNhaCungCap = ?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maNCC);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi kiểm tra nhà cung cấp: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}
