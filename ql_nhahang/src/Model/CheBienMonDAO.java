package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CheBienMonDAO {

    public void themCheBienMon(CheBienMon cb) {
        String sql = "INSERT INTO chebienmonan(MaCheBienMonAn, MaMon, SoBan, MaHoaDonBanHang, TinhTrang) VALUES(?, ?, ?, ?, ?)";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cb.getMaCheBien());
            ps.setString(2, cb.getMaMon());
            ps.setInt(3, cb.getSoBan());
            ps.setString(4, cb.getMaHoaDonBanHang());
            ps.setString(5, cb.getTinhTrang());
            ps.executeUpdate();
            System.out.println("✅ Thêm chế biến món thành công!");
        } catch (SQLException e) {
            System.out.println("❌ Thêm chế biến món thất bại!");
            e.printStackTrace();
        }
    }

    public void suaCheBienMon(CheBienMon cb) {
        String sql = "UPDATE chebienmonan SET MaMon=?, SoBan=?, MaHoaDonBanHang=?, TinhTrang=? WHERE MaCheBienMonAn=?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cb.getMaMon());
            ps.setInt(2, cb.getSoBan());
            ps.setString(3, cb.getMaHoaDonBanHang());
            ps.setString(4, cb.getTinhTrang());
            ps.setString(5, cb.getMaCheBien());
            ps.executeUpdate();
            System.out.println("✅ Cập nhật chế biến món thành công!");
        } catch (SQLException e) {
            System.out.println("❌ Cập nhật chế biến món thất bại!");
            e.printStackTrace();
        }
    }

    public void xoaCheBienMon(String maCheBien) {
        String sql = "DELETE FROM chebienmonan WHERE MaCheBienMonAn=?";
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
        String sql = "SELECT * FROM chebienmonan";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                CheBienMon cb = new CheBienMon();
                cb.setMaCheBien(rs.getString("MaCheBienMonAn"));
                cb.setMaMon(rs.getString("MaMon"));
                cb.setSoBan(rs.getInt("SoBan"));
                cb.setMaHoaDonBanHang(rs.getString("MaHoaDonBanHang"));
                cb.setTinhTrang(rs.getString("TinhTrang"));
                list.add(cb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}