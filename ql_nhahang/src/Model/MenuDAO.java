package model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDAO {
    public void themMenu(Menu menu) {
        String sql = "INSERT INTO menu(MaMon,TenMon,GiaTien,TinhTrangMon,SoLuong) VALUES(?,?,?,?,?)";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, menu.getMaMon());
            ps.setString(2, menu.getTenMon());
            ps.setBigDecimal(3, menu.getGiaTien());  // GiaTien đúng vị trí cột thứ 3
            ps.setString(4, menu.getTinhTrangMon()); // TinhTrangMon đúng vị trí cột thứ 4
            ps.setInt(5, menu.getSoLuong());
                
            ps.executeUpdate();
            System.out.println("Thêm món thành công!");
        } catch (Exception e) {
            System.out.println("Thêm món thất bại!");
            e.printStackTrace();
        }
    }
    
    public void suaMenu(Menu menu) {
        String sql = "UPDATE menu SET TenMon = ?, GiaTien = ?, TinhTrangMon = ?, SoLuong = ? WHERE MaMon = ?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, menu.getTenMon());
            ps.setBigDecimal(2, menu.getGiaTien());
            ps.setString(3, menu.getTinhTrangMon());
            ps.setInt(4, menu.getSoLuong());
            ps.setString(5, menu.getMaMon());  // WHERE MaMon = ?
    
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Cập nhật món thành công!");
            } else {
                System.out.println("⚠️ Không tìm thấy món để cập nhật!");
            }
        } catch (Exception e) {
            System.out.println("❌ Cập nhật món thất bại!");
            e.printStackTrace();
        }
    }
    
    public void xoaMenu(String maMon) {
        String sql = "DELETE FROM menu WHERE MaMon = ?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maMon);
    
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("✅ Xóa món thành công!");
            } else {
                System.out.println("⚠️ Không tìm thấy món để xóa!");
            }
        } catch (Exception e) {
            System.out.println("❌ Xóa món thất bại!");
            e.printStackTrace();
        }
    }
    
    public List<Menu> getMenu() {
        List<Menu> list = new ArrayList<>();
        String sql = "SELECT * FROM menu";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Menu menu = new Menu();
                menu.setMaMon(rs.getString("MaMon"));
                menu.setTenMon(rs.getString("TenMon"));
                menu.setGiaTien(rs.getBigDecimal("GiaTien"));
                menu.setTinhTrangMon(rs.getString("TinhTrangMon"));
                menu.setSoLuong(rs.getInt("SoLuong"));
                list.add(menu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
}
