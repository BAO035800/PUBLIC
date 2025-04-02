package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class TienLuongDAO {
    public void themTienLuong(TienLuong tienluong) {
        String sql = "INSERT INTO tienluong(MaLuong, MaNhanVien, TenNhanVien, TinhTrangLuong, GioLamViec, GhiChu) VALUES(?,?,?,?,?,?)";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tienluong.getMaLuong());
            ps.setString(2, tienluong.getMaNhanVien());
            ps.setString(3, tienluong.getTenNhanVien());
            ps.setString(4, tienluong.getTinhTrangLuong());
            ps.setInt(5, tienluong.getGioLamViec());
            ps.setString(6, tienluong.getGhiChu());
            ps.executeUpdate();
            System.out.println("Thêm lương nhân viên thành công!");
        } catch (Exception e) {
            System.out.println("Thêm lương nhân viên thất bại!");
            e.printStackTrace();
        }
    }

    public void suaTienLuong(TienLuong tienluong) {
        String sql = "UPDATE tienluong SET MaNhanVien = ?, TenNhanVien = ?, TinhTrangLuong = ?, GioLamViec = ?, GhiChu = ? WHERE MaLuong = ?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tienluong.getMaNhanVien());
            ps.setString(2, tienluong.getTenNhanVien());
            ps.setString(3, tienluong.getTinhTrangLuong());
            ps.setInt(4, tienluong.getGioLamViec());
            ps.setString(5, tienluong.getGhiChu());
            ps.setString(6, tienluong.getMaLuong());

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cập nhật thông tin lương thành công!");
            } else {
                System.out.println("Không tìm thấy bản ghi để cập nhật!");
            }
        } catch (Exception e) {
            System.out.println("Cập nhật thông tin lương thất bại!");
            e.printStackTrace();
        }
    }

    public boolean xoaTienLuong(String maLuong) {
        String sql = "DELETE FROM tienluong WHERE MaLuong = ?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maLuong);

            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "Xóa thông tin tiền lương thành công!");
                return true;
            } else {
                System.out.println("Không tìm thấy bản ghi để xóa!");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Xóa thông tin tiền lương thất bại! Lỗi: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<TienLuong> getTienLuong() {
        List<TienLuong> list = new ArrayList<>();
        String sql = "SELECT * FROM tienluong";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                TienLuong tienluong = new TienLuong();
                tienluong.setMaLuong(rs.getString("MaLuong"));
                tienluong.setMaNhanVien(rs.getString("MaNhanVien"));
                tienluong.setTenNhanVien(rs.getString("TenNhanVien"));
                tienluong.setTinhTrangLuong(rs.getString("TinhTrangLuong"));
                tienluong.setGioLamViec(rs.getInt("GioLamViec"));
                tienluong.setGhiChu(rs.getString("GhiChu"));
                list.add(tienluong);
            }
        } catch (Exception e) {
            System.out.println("Lấy danh sách lương thất bại!");
            e.printStackTrace();
        }
        return list;
    }
}
