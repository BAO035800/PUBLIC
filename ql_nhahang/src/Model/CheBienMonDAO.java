package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CheBienMonDAO {

    public void themCheBienMon(CheBienMon cb) {
        String sql = "INSERT INTO chebienmon(MaCheBien, MaMon, MaHoaDonBanHang, TrangThai) VALUES(?, ?, ?, ?)";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cb.getMaCheBien());
            ps.setString(2, cb.getMaMon());
            ps.setString(3, cb.getMaHoaDonBanHang());
            ps.setString(4, cb.getTrangThai());
            ps.executeUpdate();
            System.out.println("✅ Thêm chế biến món thành công!");
        } catch (SQLException e) {
            System.out.println("❌ Thêm chế biến món thất bại!");
            e.printStackTrace();
        }
    }

    public void suaCheBienMon(CheBienMon cb) {
        String sql = "UPDATE chebienmon SET MaMon=?, MaHoaDonBanHang=?, TrangThai=? WHERE MaCheBien=?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cb.getMaMon());
            ps.setString(2, cb.getMaHoaDonBanHang());
            ps.setString(3, cb.getTrangThai());
            ps.setString(4, cb.getMaCheBien());
            ps.executeUpdate();
            System.out.println("✅ Cập nhật chế biến món thành công!");
        } catch (SQLException e) {
            System.out.println("❌ Cập nhật chế biến món thất bại!");
            e.printStackTrace();
        }
    }

    public void xoaCheBienMon(String maCheBien) {
        String sql = "DELETE FROM chebienmon WHERE MaCheBien=?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maCheBien);
            ps.executeUpdate();
            System.out.println("✅ Xóa chế biến món thành công!");
        } catch (SQLException e) {
            System.out.println("❌ Xóa chế biến món thất bại!");
            e.printStackTrace();
        }
    }

    public List<CheBienMon> getDanhSachCheBien() {
        List<CheBienMon> list = new ArrayList<>();
        String sql = "SELECT * FROM chebienmon";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                CheBienMon cb = new CheBienMon();
                cb.setMaCheBien(rs.getString("MaCheBien"));
                cb.setMaMon(rs.getString("MaMon"));
                cb.setMaHoaDonBanHang(rs.getString("MaHoaDonBanHang"));
                cb.setTrangThai(rs.getString("TrangThai"));
                list.add(cb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
