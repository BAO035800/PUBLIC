package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HoaDonNhapDAO {
    public void themHoaDonNhap(HoaDonNhap hoadonnhap) {
        String sql = "INSERT INTO hoadonnhap (MaHoaDonKho, MaNguyenLieu, MaNhaCungCap) VALUES (?, ?, ?)";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, hoadonnhap.getMaHoaDonKho());
            ps.setString(2, hoadonnhap.getMaNguyenLieu());
            ps.setString(3, hoadonnhap.getMaNhaCungCap());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void suaHoaDonNhap(HoaDonNhap hoadonnhap) {
        String sql = "UPDATE hoadonnhap SET MaNguyenLieu = ?, MaNhaCungCap = ? WHERE MaHoaDonKho = ?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, hoadonnhap.getMaNguyenLieu());
            ps.setString(2, hoadonnhap.getMaNhaCungCap());
            ps.setString(5, hoadonnhap.getMaHoaDonKho());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void xoaHoaDonNhap(String MaHoaDonKho) {
        String sql = "DELETE FROM hoadonnhap WHERE MaHoaDonKho = ?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, MaHoaDonKho);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<HoaDonNhap> layDanhSachHoaDonNhap() {
        List<HoaDonNhap> list = new ArrayList<>();
        String sql = "SELECT * FROM hoadonnhap";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                HoaDonNhap hoadonnhap = new HoaDonNhap();
                hoadonnhap.setMaHoaDonKho(rs.getString("MaHoaDonKho"));
                hoadonnhap.setMaNguyenLieu(rs.getString("MaNguyenLieu"));
                hoadonnhap.setMaNhaCungCap(rs.getString("MaNhaCungCap"));
                list.add(hoadonnhap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}