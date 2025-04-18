package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhanCongBepDAO {

    public void themPhanCong(PhanCongBep pc) {
        String sql = "INSERT INTO phancongbep(MaCheBien, MaNhanVien, VaiTro) VALUES(?, ?, ?)";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pc.getMaCheBien());
            ps.setString(2, pc.getMaNhanVien());
            ps.setString(3, pc.getVaiTro());
            ps.executeUpdate();
            System.out.println("✅ Thêm phân công bếp thành công!");
        } catch (SQLException e) {
            System.out.println("❌ Thêm phân công bếp thất bại!");
            e.printStackTrace();
        }
    }

    public void suaPhanCong(PhanCongBep pc) {
        String sql = "UPDATE phancongbep SET VaiTro=? WHERE MaCheBien=? AND MaNhanVien=?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pc.getVaiTro());
            ps.setString(2, pc.getMaCheBien());
            ps.setString(3, pc.getMaNhanVien());
            ps.executeUpdate();
            System.out.println("✅ Cập nhật phân công bếp thành công!");
        } catch (SQLException e) {
            System.out.println("❌ Cập nhật phân công bếp thất bại!");
            e.printStackTrace();
        }
    }

    public void xoaPhanCong(String maCheBien, String maNhanVien) {
        String sql = "DELETE FROM phancongbep WHERE MaCheBien=? AND MaNhanVien=?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maCheBien);
            ps.setString(2, maNhanVien);
            ps.executeUpdate();
            System.out.println("✅ Xóa phân công bếp thành công!");
        } catch (SQLException e) {
            System.out.println("❌ Xóa phân công bếp thất bại!");
            e.printStackTrace();
        }
    }

    public List<PhanCongBep> getDanhSachPhanCong() {
        List<PhanCongBep> list = new ArrayList<>();
        String sql = "SELECT * FROM phancongbep";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PhanCongBep pc = new PhanCongBep();
                pc.setMaCheBien(rs.getString("MaCheBien"));
                pc.setMaNhanVien(rs.getString("MaNhanVien"));
                pc.setVaiTro(rs.getString("VaiTro"));
                list.add(pc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public boolean exists(String maCheBien, String maNhanVien) {
        String sql = "SELECT * FROM phancongbep WHERE MaCheBien = ? AND MaNhanVien = ?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maCheBien);
            ps.setString(2, maNhanVien);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // Nếu có bản ghi thì tồn tại
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
