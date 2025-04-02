package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KhoNguyenLieuDAO {
    
    public void themNguyenLieu(KhoNguyenLieu khonguyenlieu) {
        String sql = "INSERT INTO khonguyenlieu (MaNguyenLieu, TenNguyenLieu, MaNhaCungCap, GiaTien, SoLuong) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, khonguyenlieu.getMaNguyenLieu());
            ps.setString(2, khonguyenlieu.getTenNguyenLieu());
            ps.setString(3, khonguyenlieu.getMaNhaCungCap());
            ps.setBigDecimal(4, khonguyenlieu.getGiaNhap()); 
            ps.setInt(5, khonguyenlieu.getSoLuong());
            ps.executeUpdate();
            System.out.println("Thêm nguyên liệu thành công!");
        } catch (Exception e) {
            System.out.println("Thêm nguyên liệu thất bại!");
            e.printStackTrace();
        }
    }
    
    public void suaNguyenLieu(KhoNguyenLieu khonguyenlieu) {
        String sql = "UPDATE khonguyenlieu SET TenNguyenLieu = ?, MaNhaCungCap = ?, GiaTien = ?, SoLuong = ? WHERE MaNguyenLieu = ?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, khonguyenlieu.getTenNguyenLieu());
            ps.setString(2, khonguyenlieu.getMaNhaCungCap());
            ps.setBigDecimal(3, khonguyenlieu.getGiaNhap()); 
            ps.setInt(4, khonguyenlieu.getSoLuong());
            ps.setString(5, khonguyenlieu.getMaNguyenLieu());

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cập nhật thông tin nguyên liệu thành công!");
            } else {
                System.out.println("Không tìm thấy bản ghi để cập nhật!");
            }
        } catch (Exception e) {
            System.out.println("Cập nhật thông tin nguyên liệu thất bại!");
            e.printStackTrace();
        }
    }
    
    public void xoaNguyenLieu(String maNguyenLieu) {
        String sql = "DELETE FROM khonguyenlieu WHERE MaNguyenLieu = ?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maNguyenLieu);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Xóa nguyên liệu thành công!");
            } else {
                System.out.println("Không tìm thấy bản ghi để xóa!");
            }
        } catch (Exception e) {
            System.out.println("Xóa nguyên liệu thất bại!");
            e.printStackTrace();
        }
    }

    public List<KhoNguyenLieu> getKhoNguyenLieu() {
        List<KhoNguyenLieu> list = new ArrayList<>();
        String sql = "SELECT * FROM khonguyenlieu";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                KhoNguyenLieu khonguyenlieu = new KhoNguyenLieu();
                khonguyenlieu.setMaNguyenLieu(rs.getString("MaNguyenLieu"));
                khonguyenlieu.setTenNguyenLieu(rs.getString("TenNguyenLieu"));
                khonguyenlieu.setMaNhaCungCap(rs.getString("MaNhaCungCap"));
                khonguyenlieu.setGiaNhap(rs.getBigDecimal("GiaNhap")); 
                khonguyenlieu.setSoLuong(rs.getInt("SoLuong"));
                list.add(khonguyenlieu);
            }
        } catch (Exception e) {
            System.out.println("Lấy danh sách nguyên liệu thất bại!");
            e.printStackTrace();
        }
        return list;
    } 
}
