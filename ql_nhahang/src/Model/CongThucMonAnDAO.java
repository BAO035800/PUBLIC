package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CongThucMonAnDAO {

    public void themCongThuc(CongThucMonAn ct) {
        String sql = "INSERT INTO congthucmonan(MaMon, MaNguyenLieu, SoLuong) VALUES(?, ?, ?)";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ct.getMaMon());
            ps.setString(2, ct.getMaNguyenLieu());
            ps.setFloat(3, ct.getSoLuong());
            ps.executeUpdate();
            System.out.println("✅ Thêm công thức món ăn thành công!");
        } catch (SQLException e) {
            System.out.println("❌ Thêm công thức món ăn thất bại!");
            e.printStackTrace();
        }
    }

    public void suaCongThuc(CongThucMonAn ct) {
        String sql = "UPDATE congthucmonan SET SoLuong=? WHERE MaMon=? AND MaNguyenLieu=?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setFloat(1, ct.getSoLuong());
            ps.setString(2, ct.getMaMon());
            ps.setString(3, ct.getMaNguyenLieu());
            ps.executeUpdate();
            System.out.println("✅ Cập nhật công thức thành công!");
        } catch (SQLException e) {
            System.out.println("❌ Cập nhật công thức thất bại!");
            e.printStackTrace();
        }
    }

    public void xoaCongThuc(String maMon, String maNguyenLieu) {
        String sql = "DELETE FROM congthucmonan WHERE MaMon=? AND MaNguyenLieu=?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maMon);
            ps.setString(2, maNguyenLieu);
            ps.executeUpdate();
            System.out.println("✅ Xóa công thức thành công!");
        } catch (SQLException e) {
            System.out.println("❌ Xóa công thức thất bại!");
            e.printStackTrace();
        }
    }

    public List<CongThucMonAn> getDanhSachCongThuc() {
        List<CongThucMonAn> list = new ArrayList<>();
        String sql = "SELECT * FROM congthucmonan";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                CongThucMonAn ct = new CongThucMonAn();
                ct.setMaMon(rs.getString("MaMon"));
                ct.setMaNguyenLieu(rs.getString("MaNguyenLieu"));
                ct.setSoLuong(rs.getFloat("SoLuong"));
                list.add(ct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // ✅ Hàm kiểm tra công thức đã tồn tại hay chưa
    public boolean exists(String maMon, String maNguyenLieu) {
        String sql = "SELECT 1 FROM congthucmonan WHERE MaMon = ? AND MaNguyenLieu = ?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maMon);
            ps.setString(2, maNguyenLieu);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // true nếu tồn tại dòng kết quả
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
