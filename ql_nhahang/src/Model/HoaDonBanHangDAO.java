package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HoaDonBanHangDAO {
    // Thêm hóa đơn bán hàng
    public void themHoaDon(HoaDonBanHang hoaDon) {
        String sql = "INSERT INTO hoadonbanhang(MaHoaDonBanHang, MaMon, TenMon, GiaTien, SoBan, TongTienHoaDon, GhiChu) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, hoaDon.getMaHoaDonBanHang());
            ps.setString(2, hoaDon.getMaMon());
            ps.setString(3, hoaDon.getTenMon());
            ps.setBigDecimal(4, hoaDon.getGiaTien());
            ps.setInt(5, hoaDon.getSoBan());
            ps.setBigDecimal(6, hoaDon.getTongTienHoaDon());
            ps.setString(7, hoaDon.getGhiChu());
            ps.executeUpdate();
            System.out.println("✅ Thêm hóa đơn thành công!");
        } catch (SQLException e) {
            System.out.println("❌ Thêm hóa đơn thất bại!");
            e.printStackTrace();
        }
    }

    // Sửa hóa đơn bán hàng
    public void suaHoaDon(HoaDonBanHang hoaDon) {
        String sql = "UPDATE hoadonbanhang SET MaMon=?, TenMon=?, GiaTien=?, SoBan=?, TongTienHoaDon=?, GhiChu=? WHERE MaHoaDonBanHang=?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, hoaDon.getMaMon());
            ps.setString(2, hoaDon.getTenMon());
            ps.setBigDecimal(3, hoaDon.getGiaTien());
            ps.setInt(4, hoaDon.getSoBan());
            ps.setBigDecimal(5, hoaDon.getTongTienHoaDon());
            ps.setString(6, hoaDon.getGhiChu());
            ps.setString(7, hoaDon.getMaHoaDonBanHang());
            ps.executeUpdate();
            System.out.println("✅ Cập nhật hóa đơn thành công!");
        } catch (SQLException e) {
            System.out.println("❌ Cập nhật hóa đơn thất bại!");
            e.printStackTrace();
        }
    }

    // Xóa hóa đơn bán hàng
    public void xoaHoaDon(String maHoaDon) {
        String sql = "DELETE FROM hoadonbanhang WHERE MaHoaDonBanHang=?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maHoaDon);
            ps.executeUpdate();
            System.out.println("✅ Xóa hóa đơn thành công!");
        } catch (SQLException e) {
            System.out.println("❌ Xóa hóa đơn thất bại!");
            e.printStackTrace();
        }
    }
    // Lấy danh sách hóa đơn
    public List<HoaDonBanHang> getDanhSachHoaDon() {
        List<HoaDonBanHang> list = new ArrayList<>();
        String sql = "SELECT * FROM hoadonbanhang";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                HoaDonBanHang hoaDon = new HoaDonBanHang();
                hoaDon.setMaHoaDonBanHang(rs.getString("MaHoaDonBanHang"));
                hoaDon.setMaMon(rs.getString("MaMon"));
                hoaDon.setTenMon(rs.getString("TenMon"));
                hoaDon.setGiaTien(rs.getBigDecimal("GiaTien"));
                hoaDon.setSoBan(rs.getInt("SoBan"));
                hoaDon.setTongTienHoaDon(rs.getBigDecimal("TongTienHoaDon"));
                hoaDon.setGhiChu(rs.getString("GhiChu"));
                list.add(hoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
