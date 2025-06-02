package controller;

import java.sql.*;
import javax.swing.JOptionPane;
import model.Connect;

public class KTBanHang {
    
    // Kiểm tra bàn
    public boolean kiemTraBanTonTai(String soBan) {
        String sql = "SELECT COUNT(*) FROM ban WHERE SoBan = ?"; 
        try (Connection conn = Connect.getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, soBan); 
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "❌ Lỗi kiểm tra bàn: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // Kiểm tra món
    public boolean kiemTraMonTonTai(String maMon) {
        String sql = "SELECT COUNT(*) FROM menu WHERE MaMon = ?"; 
        try (Connection conn = Connect.getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, maMon);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "❌ Lỗi kiểm tra món: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // ✅ Thêm kiểm tra mã hóa đơn
    public boolean kiemTraMaHoaDon(String maHD) {
        String sql = "SELECT COUNT(*) FROM hoadonbanhang WHERE MaHoaDonBanHang = ?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maHD);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "❌ Lỗi kiểm tra hóa đơn: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}
