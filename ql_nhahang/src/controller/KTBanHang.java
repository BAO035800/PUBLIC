package controller;

import java.sql.*;
import javax.swing.JOptionPane;
import model.Connect;

public class KTBanHang {
    
    // Kiểm tra xem bàn có tồn tại hay không (bàn là String)
    public boolean kiemTraBanTonTai(String soBan) {
        String sql = "SELECT COUNT(*) FROM ban WHERE SoBan = ?"; 
        try (Connection conn = Connect.getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, soBan); // Dùng setString vì SoBan là String
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;  // Trả về true nếu COUNT > 0
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "❌ Lỗi kiểm tra bàn: " + e.getMessage());
            e.printStackTrace();
        }
        return false;  // Trả về false nếu có lỗi hoặc bàn không tồn tại
    }

    // Kiểm tra xem món có tồn tại trong menu hay không
    public boolean kiemTraMonTonTai(String maMon) {
        String sql = "SELECT COUNT(*) FROM menu WHERE MaMon = ?"; 
        try (Connection conn = Connect.getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, maMon);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;  // Trả về true nếu COUNT > 0
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "❌ Lỗi kiểm tra món: " + e.getMessage());
            e.printStackTrace();
        }
        return false;  // Trả về false nếu có lỗi hoặc món không tồn tại
    }
}
