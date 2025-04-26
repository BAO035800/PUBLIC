package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HoaDonBanHangDAO {
    // Thêm hóa đơn bán hàng
    public void themHoaDon(HoaDonBanHang hoaDon) {
        String sql = "INSERT INTO hoadonbanhang(MaHoaDonBanHang, TrangThai) VALUES(?, ?)";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, hoaDon.getMaHoaDonBanHang());
            ps.setString(2, hoaDon.getTinhTrang());
            ps.executeUpdate();
            System.out.println("✅ Thêm hóa đơn thành công!");
        } catch (SQLException e) {
            System.out.println("❌ Thêm hóa đơn thất bại!");
            e.printStackTrace();
        }
    }

    // Sửa hóa đơn bán hàng
    public void suaHoaDon(HoaDonBanHang hoaDon) {
        String sql = "UPDATE hoadonbanhang SET  TrangThai=? WHERE MaHoaDonBanHang=?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, hoaDon.getTinhTrang());
            ps.setString(2, hoaDon.getMaHoaDonBanHang());
            ps.executeUpdate();
            System.out.println("✅ Sửa hóa đơn thành công!");
        } catch (SQLException e) {
            System.out.println("❌ Sửa hóa đơn thất bại!");
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
                hoaDon.setSoBan(rs.getInt("SoBan"));
                hoaDon.setTongTienHoaDon(rs.getBigDecimal("TongTienHoaDon"));
                hoaDon.setGhiChu(rs.getString("TinhTrang"));
                list.add(hoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
